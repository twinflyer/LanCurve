package de.lanalda.suff.curve.client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.lanalda.suff.curve.common.Player;

public class ClientNetworking {

	private static ClientNetworking instance;

	private String serverIp;

	public ClientNetworking() {
		
	}

	public static void init(String serverIp) {
		getInstance().serverIp = serverIp;
	}

	public Color registerPlayer(String name) {
		HttpURLConnection httpConnection = null;
		try {
			// Create connection
			URL url = new URL("http://" + this.serverIp + ":" + 8080
					+ "/suff/register?name=" + name);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", "text/plain");
			httpConnection.setRequestProperty("Content-Language", "en-US");

			httpConnection.setUseCaches(false);
			httpConnection.setDoOutput(true);

			Color c = new Color(Integer.parseInt(getResponse(httpConnection).trim()));
			return c;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpConnection != null) {
				httpConnection.disconnect();
			}
		}
		return null;
	}

	public void startPlayerUpdateThread(Player player) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					HttpURLConnection httpConnection = null;
					while(true) {
						try {
							// Create connection
							URL url = new URL("http://" + serverIp + ":" + 8080
									+ "/suff/setposition?name=" + player.getId() + "&x="
									+ player.getPosition().getxPos() + "&y="
									+ player.getPosition().getyPos());
							httpConnection = (HttpURLConnection) url.openConnection();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (httpConnection != null) {
								httpConnection.disconnect();
							}
						}
						try {
							Thread.sleep(32);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
	}
	
	public void startUpdateRemotePlayerThread(RemotePlayerUpdateListener listener) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Gson gson = new Gson();
				Type type = new TypeToken<Map<String,Player>>(){}.getType();
				HttpURLConnection httpConnection = null;
				while(true) {

					try {
						// Create connection
						URL url = new URL("http://" + serverIp + ":" + 8080
								+ "/suff/players");
						httpConnection = (HttpURLConnection) url.openConnection();
						String response = getResponse(httpConnection);
						Map<String, Player> playermap = gson.fromJson(response, type);
						listener.update(playermap);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (httpConnection != null) {
							httpConnection.disconnect();
						}
					}
					
					try {
						Thread.sleep(32);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

	private String getResponse(HttpURLConnection connection) {
		// Get Response
		StringBuilder response;
		InputStream is;
		try {
			is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			response = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ClientNetworking getInstance() {
		if (ClientNetworking.instance == null)
			ClientNetworking.instance = new ClientNetworking();
		return instance;
	}
	
	public interface RemotePlayerUpdateListener {
		
		void update(Map<String, Player> map);
		
	}

}
