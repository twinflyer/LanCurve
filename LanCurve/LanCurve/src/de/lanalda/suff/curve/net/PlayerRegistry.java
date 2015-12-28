package de.lanalda.suff.curve.net;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import de.lanalda.suff.curve.common.Player;

public class PlayerRegistry {
	
	private Map<String, Player> players;
	
	public PlayerRegistry()
	{
		this.players = new HashMap<String, Player>();
	}
	
	public void register(Player player)
	{
		checkNotNull(player, "player mustn't be null");
		if(!players.containsKey(player.getId())){
			players.put(player.getId(), player);
		}
	}
	
	public Map<String, Player> getPlayers()
	{
		return players;
	}

	public Player getPlayerById(String id) {
		return players.get(id);
	}

}
