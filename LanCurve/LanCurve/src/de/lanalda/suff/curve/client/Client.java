package de.lanalda.suff.curve.client;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.lanalda.suff.curve.client.ClientNetworking.RemotePlayerUpdateListener;
import de.lanalda.suff.curve.common.Player;
import de.lanalda.suff.curve.common.Vector2D;

public class Client implements RemotePlayerUpdateListener {

	private JFrame frame;
	private LocalPlayer localPlayer;
	private Map<String, RemotePlayer> remotePlayers;
	private Panel panel;

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public Client() {
		setupFrame();
		this.remotePlayers = new HashMap<String, RemotePlayer>();

		this.startGameLoop();
	}

	private void setupFrame() {
		String ip = JOptionPane.showInputDialog(null, "Gib die IP ein");
		ClientNetworking.init(ip);
		String name = JOptionPane.showInputDialog(null, "Gib name ein");
		Color c = ClientNetworking.getInstance().registerPlayer(name);

		this.localPlayer = new LocalPlayer(this, c, name);

		this.panel = new Panel(this);

		this.frame = new JFrame("LanCurve");
		this.frame.setSize(1000, 800);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.setIgnoreRepaint(true);
		this.frame.add(this.panel);
		this.panel.requestFocus();
	}

	private void startGameLoop() {

		ClientNetworking.getInstance().startUpdateRemotePlayerThread(this);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					Client.this.act();

					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void act() {

		if (!checkCollision()) {
			localPlayer.act();
		}

		// redraw frame
		this.panel.draw();
	}

	private boolean checkCollision() {
		Vector2D colCheckPos = new Vector2D(localPlayer.getPosition());
		Vector2D direction = new Vector2D(localPlayer.getDirection());
		direction.multiply(1.25);
		colCheckPos.add(direction);

		return this.panel.isObstructed(colCheckPos);
	}

	public LocalPlayer getLocalPlayer() {
		return this.localPlayer;
	}

	public Map<String, RemotePlayer> getRemotePlayers() {
		return this.remotePlayers;
	}

	public static void main(String[] args) {
		new Client();
	}

	@Override
	public void update(Map<String, Player> map) {
		for (String key : map.keySet()) {
			Player player = map.get(key);
			if (!this.remotePlayers.containsKey(key) && !key.equals(localPlayer.getId())) {
				RemotePlayer p = new RemotePlayer();
				p.setId(player.getId());
				p.setColor(player.getColor());
				this.remotePlayers.put(key, p);
			}
			if (!key.equals(localPlayer.getId()))
				this.remotePlayers.get(key).setPosition(player.getPosition());
		}
	}
}
