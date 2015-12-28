package de.lanalda.suff.curve.common;

public class Player {
	
	private Vector2D position;
	private String id;
	
	public Player(Vector2D position, String id) {
		this.position = position;
		this.id = id;
	}
	
	public Vector2D getPosition() {
		return position;
	}
	public void setPosition(Vector2D position) {
		this.position = position;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
