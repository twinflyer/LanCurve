package de.lanalda.suff.curve.client;

import de.lanalda.suff.curve.common.Player;
import de.lanalda.suff.curve.common.Vector2D;

import java.awt.*;

public class RemotePlayer extends Player {
	
	public RemotePlayer() {
		super(new Vector2D(300, 300), "remote", Color.CYAN.getRGB());
	}

}
