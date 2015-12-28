package de.lanalda.suff.curve.client;

import de.lanalda.suff.curve.common.Player;
import de.lanalda.suff.curve.common.Vector2D;

import java.awt.*;

public class LocalPlayer extends Player {
	
	private Vector2D direction;
	private boolean right, left;
	
	public LocalPlayer(Client client, Color c, String name) {
		super(new Vector2D(100, 100), name, c.getRGB());
		this.direction = new Vector2D(5, 0);
		ClientNetworking.getInstance().startPlayerUpdateThread(this);
	}

	public void act() {
		
		float rot = 0;
		if (right)
			rot += 8;
		if (left)
			rot -= 8;
		
		this.direction.rotate(rot);
		
		this.getPosition().add(direction);
		
		//TODO send new pos to server
		
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
