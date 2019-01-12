
import java.util.*;

import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.*;


public class Game extends Application {
	private Stage primaryStage;
	private Scene scene;
	private Player player;
	private MediaPlayer music;
	private StackPane root;
	private BorderPane border;
	private HBox topMenu;
	private Controller controller;
	private static boolean sharka = true;
	private boolean gameOn = true;
	
	double x = 0;
	double y = 0;

	ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/res/background.jpg"), 900, 900, true, true));

	ArrayList<Fish> enemies = new ArrayList<Fish>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Frame frame = new Frame(primaryStage);
		root = new StackPane();
		border = new BorderPane();
		topMenu = new HBox();

		Label score = new Label();
		score.setAlignment(Pos.TOP_RIGHT); 
		score.setTextAlignment(TextAlignment.CENTER);	
		
		controller = new Controller();
		
		player = new Player();
	
		border.setTop(topMenu);	//this will contain the score label
		
		root.getChildren().add(background);
		root.getChildren().add(player);
		root.getChildren().add(topMenu);

		scene = new Scene(root, 800, 600, Color.ALICEBLUE);
		
		controller.setKeys(scene);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//handles controller movement. 
		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long time) {

				if(controller.moveUp) { y -= 1; }
				if(controller.moveDown) { y += 1; }
				
				if(controller.moveRight) { 
					x += 1;
					player.flipRight(); // when player moves, sprite flips
				}
				if(controller.moveLeft) { 
					x -= 1;
					player.flipLeft();
				}
				
				// Needs to be fixed
				populateEnemies();
				time = 0;	
			
				player.updateLocation(x, y);
				updateFish();
			}
			
		};
		
		at.start();
	}
	
	private void updateFish() {
		for(Fish fish : enemies) {
			//handles all movement of fish.. ?
			if(checkCollision(fish)) {
				System.out.println("Collided!");
				removeFish(fish);
			}
			
			if(fish.getX() > 800) {
				System.out.println("Remove fish");
			}
		}
	}
	
	private boolean checkCollision(Fish fish) {
		double minX = fish.getLocationX();
		double maxX = fish.getLocationX() + fish.getWidth();
		double minY = fish.getLocationY();
		double maxY = fish.getLocationY() + fish.getHeight();
		
		if(player.getLocationX() >= minX && player.getLocationX() < maxX) {
			if(player.getLocationY() >= minY || player.getLocationY() < maxY) {
				return true;
			}
		}
		
		return false;
	}

	private void addFish(Fish fish) {
		enemies.add(fish);
		root.getChildren().add(fish);
	}
	
	private void removeFish(Fish fish) {
		//enemies.remove(fish);
		//root.getChildren().remove(fish);
		System.out.println("removed!");
	}
	
	public void gameOver() {
	}
	
	private void populateEnemies() {
		if(sharka) {
			Fish shark = new Shark();
			
			addFish(shark);
			sharka = false;
		}
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
}
