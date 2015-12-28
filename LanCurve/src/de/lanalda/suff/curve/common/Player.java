package de.lanalda.suff.curve.common;

public class Player {
	
	private Position position;
	private String id;
	
	public Player(Position position, String id) {
		this.position = position;
		this.id = id;
	}
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
