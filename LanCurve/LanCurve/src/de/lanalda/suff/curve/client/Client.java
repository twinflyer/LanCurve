package de.lanalda.suff.curve.client;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import de.lanalda.suff.curve.common.Vector2D;

public class Client {

	private JFrame frame;
	private LocalPlayer localPlayer;
	private List<RemotePlayer> remotePlayers;
	private Panel panel;

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public Client() {
		setupFrame();
		this.localPlayer = new LocalPlayer(this);
		this.remotePlayers = new ArrayList<RemotePlayer>();

		this.startGameLoop();
	}

	private void setupFrame() {
		String ip = JOptionPane.showInputDialog(null, "Gib die IP ein");
		String name = JOptionPane.showInputDialog(null, "Gib name ein");


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

		if(!checkCollision())
		{
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

	public List<RemotePlayer> getRemotePlayers() {
		return this.remotePlayers;
	}

	public static void main(String[] args) {
		new Client();
	}

}
