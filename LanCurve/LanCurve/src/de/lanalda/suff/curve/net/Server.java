package de.lanalda.suff.curve.net;

import java.awt.*;
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
	static PlayerRegistry playerRegistry = new PlayerRegistry();
	
	
	public Server()
	{
		this.gson = new Gson();
	}
	
	@Path("players")
	@GET
	@Produces("text/plain")
	public String getPlayers()
	{
		return gson.toJson(playerRegistry.getPlayers());
	}
	
	@Path("setposition")
	@GET
	@Produces("text/plain")
	public String setPositionOf(@QueryParam("name") String name, @QueryParam("x") String x, @QueryParam("y") String y)
	{
		Vector2D pos = new Vector2D(Double.parseDouble(x), Double.parseDouble(y));
		playerRegistry.getPlayerById(name).setPosition(pos);
		
		return "Position gesetzt";
	}
	
	@Path("register")
	@GET
	@Produces("text/plain")
	public String register(@QueryParam("name") String name)
	{
		Color color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		playerRegistry.register(new Player(new Vector2D((int) Math.random()*100, (int) Math.random()*100), name, color.getRGB()));
		return String.valueOf(color.getRGB());
	}

}
