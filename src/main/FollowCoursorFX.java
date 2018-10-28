package main;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FollowCoursorFX extends Application{
	private Canvas can;
	private GraphicsContext gc;
	private Kreis kreis;
	
	private byte pause;
	private double x, y;
	
	private ArrayList<Essen> essen = new ArrayList<Essen>();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		Timeline tl_draw = new Timeline(new KeyFrame(Duration.millis(16.67), e -> {
			draw();
		}));
		tl_draw.setCycleCount(Timeline.INDEFINITE);
		tl_draw.play();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 700, 700);
		
		can = new Canvas(scene.getWidth(), scene.getHeight());
		gc = can.getGraphicsContext2D();
		
		kreis = new Kreis(gc);
		root.setCenter(can);
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				x = (int) e.getX();
				y = (int) e.getY();
			}
		});
		
		scene.widthProperty().addListener((obsv, oldVal, newVal) -> {
		   can.setWidth(newVal.doubleValue());
		});
		
		scene.heightProperty().addListener((obsv, oldVal, newVal) -> {
			can.setHeight(newVal.doubleValue());
		});
		
		stage.setScene(scene);
		stage.show();
	}
	
	private void draw() {
		pause++;
		if (pause >= 50) {
			fillList(kreis.foodSpawner(1));
			pause = 0;
		}
		gc.clearRect(kreis.getX(), kreis.getY(), kreis.getSize(), kreis.getSize());
		kreis.update(x, y);
		kill();
		kreis.show();
	}
	
	private void kill() {
		Essen food;
		for (int i = 0;i < essen.size();i++) {
			food = essen.get(i);
			if (food.getX() >= kreis.getX()
					&& food.getY() >= kreis.getY()
					&& food.getX() + food.getSize() <= kreis.getX() + kreis.getSize()
					&& food.getY() + food.getSize() <= kreis.getY() + kreis.getSize()) {
				food.setDead(true);
				essen.set(i, food);
			}
		}
		for (int i = essen.size() - 1;i >= 0;i--) {
			food = essen.get(i);
			if (food.isDead()) {
				essen.remove(i);
				kreis.setSize(kreis.getSize() + food.getSize() / 10);
			}
		}
	}
	
	private void fillList(Essen [] food) {
		for(int i = 0;i < food.length;i++) {
			essen.add(food[i]);
		}
	}
}