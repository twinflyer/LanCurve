package de.lanalda.suff.curve.net;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

import de.lanalda.suff.curve.common.Player;
import de.lanalda.suff.curve.common.Vector2D;

@Path("suff")
public class Server{

	private Gson gson;
	private PlayerRegistry playerRegistry;
	static int ident = 0;
	
	
	public Server()
	{
		this.gson = new Gson();
		this.playerRegistry = new PlayerRegistry();
	}
	
	@Path("players")
	@GET
	@Produces("plain/text")
	public String getPlayers()
	{
		return gson.toJson(playerRegistry.getPlayers());
	}
	
	@GET
	@Produces("plain/text")
	public String getPositionOf(@QueryParam("player")String id)
	{
		return gson.toJson(playerRegistry.getPlayerById(id));
	}
	
	@POST
	@Consumes("plain/text")
	public void setPositionOf(String in)
	{
		String[] array = in.split(",");
		Vector2D pos = new Vector2D(Integer.getInteger(array[1]), Integer.getInteger(array[2]));
		playerRegistry.getPlayerById(array[0]).setPosition(pos);
	}
	
	@Path("register")
	@GET
	@Produces("plain/text")
	public String register()
	{
		ident ++;
		playerRegistry.register(new Player(new Vector2D((int) Math.random()*100, (int) Math.random()*100), String.valueOf(ident)));
		return String.valueOf(ident);
	}

}
