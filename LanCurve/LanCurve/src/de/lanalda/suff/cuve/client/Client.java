package de.lanalda.suff.cuve.client;

import java.util.ArrayList;

import javax.swing.JFrame;

import de.lanalda.suff.curve.common.Vector2D;

public class Client {

	private JFrame frame;
	private LocalPlayer localPlayer;
	private ArrayList<RemotePlayer> remotePlayers;
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
		this.panel = new Panel(this);

		this.frame = new JFrame("LanCurve");
		this.frame.setSize(1000, 800);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.setIgnoreRepaint(true);
		this.frame.add(this.panel);
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

		checkCollision();
		
		localPlayer.act();

		// redraw frame
		this.panel.draw();
	}

	private void checkCollision() {
		Vector2D colCheckPos = new Vector2D(localPlayer.getPosition());
		colCheckPos.add(localPlayer.getDirection());
		
		if (this.panel.isObstructed(colCheckPos)) {
			System.out.println("collide");
		}
	}

	public LocalPlayer getLocalPlayer() {
		return this.localPlayer;
	}

	public ArrayList<RemotePlayer> getRemotePlayers() {
		return this.remotePlayers;
	}

	public static void main(String[] args) {
		new Client();
	}

}