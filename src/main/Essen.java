package main;

public class Essen {
	private double x, y, size;
	private boolean dead = false;
	
	public Essen(double x, double y, double size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public void setSize(double size) {
		this.size = size;
	}
		
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getSize() {
		return size;
	}
	
	public boolean isDead() {
		return dead;
	}
}