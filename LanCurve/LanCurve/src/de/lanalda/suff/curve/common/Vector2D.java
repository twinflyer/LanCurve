package de.lanalda.suff.curve.common;

public class Vector2D {
	
	public double x;
	public double y;
	
	public Vector2D(double xPos, double yPos) {
		this.x = xPos;
		this.y = yPos;
	}
	
	public Vector2D(Vector2D vector) {
		this.x = vector.x;
		this.y = vector.y;
	}

	public double getxPos() {
		return x;
	}

	public void setxPos(double xPos) {
		this.x = xPos;
	}

	public double getyPos() {
		return y;
	}

	public void setyPos(double yPos) {
		this.y = yPos;
	}

	public Vector2D add(Vector2D direction) {
		this.x += direction.x;
		this.y += direction.y;
		return this;
	}

	public Vector2D multiply(double multiplier)
	{
		this.x *= multiplier;
		this.y *= multiplier;
		return this;
	}
	
	public void rotate(float angle) {
		double theta = Math.toRadians(angle);

		double cs = Math.cos(theta);
		double sn = Math.sin(theta);

		double px = this.x * cs - this.y * sn; 
		double py = this.x * sn + this.y * cs;
		
		this.x = px;
		this.y = py;
	}
	
	

}
