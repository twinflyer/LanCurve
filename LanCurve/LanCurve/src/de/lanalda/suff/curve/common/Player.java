package de.lanalda.suff.curve.common;

public class Player {
	
	private Vector2D position;
	private String id;
	private int color;

	public Player(Vector2D position, String id, int color) {
		this.position = position;
		this.id = id;
		this.color = color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
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
