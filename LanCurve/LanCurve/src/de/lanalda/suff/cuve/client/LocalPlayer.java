package de.lanalda.suff.cuve.client;

import de.lanalda.suff.curve.common.Player;
import de.lanalda.suff.curve.common.Vector2D;

public class LocalPlayer extends Player {
	
	private Vector2D direction;
	private boolean right, left;
	
	public LocalPlayer(Client client) {
		super(new Vector2D(100, 100), "joo");
		this.direction = new Vector2D(5, 0);
	}

	public void act() {
		
		this.getPosition().add(direction);
		
		float rot = 0;
		if (right)
			rot += 5;
		if (left)
			rot -= 5;
		
		this.direction.rotate(rot);
	}
	
	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

}
