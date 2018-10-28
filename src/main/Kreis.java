package main;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Kreis {
	private final GraphicsContext gc;
	private double x, y, size = 50, easingAmount = size / 500;
	
	public Kreis(GraphicsContext gc) {
		this.gc = gc;
		this.gc.setFill(Color.rgb(255, 0, 255));
		x = this.gc.getCanvas().getWidth() / 2 - size / 2;
		y = this.gc.getCanvas().getHeight() / 2 - size / 2;
	}
	
	public void update(double mouseX, double mouseY) {
		playerMovement(mouseX, mouseY);
		 easingAmount = 5 / size;
	}
	
	private void playerMovement(double mouseX, double mouseY) {
		double xDistance = mouseX - x - size / 2;
		double yDistance = mouseY - y - size / 2;
		double distance;
		distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		if (distance > 1) {
			x += xDistance * easingAmount;
			y += yDistance * easingAmount;
		}
	}
	
	public Essen [] foodSpawner(final int menge) {
		Random r = new Random();
		Essen essen [] = new Essen [menge];
		int ranX, ranY, ranSize;
		for(int i = 0;i < menge;i++) {
			ranSize = (r.nextInt(10) + 1) * 10;
			ranX = r.nextInt((int) gc.getCanvas().getWidth() - ranSize);
			ranY = r.nextInt((int) gc.getCanvas().getHeight() - ranSize);
			essen[i] = new Essen(ranX, ranY, ranSize);
			gc.fillOval(ranX, ranY, ranSize, ranSize);
		}
		return essen;
	}
	
	public void show() {
		gc.fillOval(x, y, size, size);
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
}