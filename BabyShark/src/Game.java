
import java.util.*;

import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	private Frame frame;
	private Scene menuScene;
	private Scene playScene;
	private Scene endScene;
	private Scene instructScene;
	private Label scoreLabel;
	private Player player;
	private MediaPlayer music;
	private StackPane root;
	private BorderPane border;
	private HBox topMenu;
	private Controller controller;
	private static boolean sharka = true;
	private boolean gameOn = true;
	private int score = 0;
	
	double x = 0;
	double y = 0;

	ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/res/background.jpg"), 900, 900, true, true));

	ArrayList<Fish> enemies = new ArrayList<Fish>(); //stores the fish that exist on screen
	HashMap<FishType, Fish> fishInfo = new HashMap<FishType, Fish>(); //stores a collection of fish database
	
	private void fillInfo() {
		fishInfo.put(FishType.PUFFERFISH, new Pufferfish());
		fishInfo.put(FishType.SHARK, new Shark(100, 100));
	}
	
	private void initialize() {
		frame = new Frame(primaryStage);
		controller = new Controller();
		player = new Player();

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		root = new StackPane();
		border = new BorderPane();
		topMenu = new HBox();
		
		scoreLabel = new Label("Score: " + Integer.toString(score));
		scoreLabel.setFont(new Font("Arial", 18.0));
		scoreLabel.setAlignment(Pos.TOP_LEFT); 
		scoreLabel.setTextAlignment(TextAlignment.LEFT);	
		initialize();
		topMenu.getChildren().add(scoreLabel);
		border.setTop(topMenu);
		
		root.getChildren().add(background);
		root.getChildren().add(player);
		root.getChildren().add(border);
		
		playScene = new Scene(root, 1000, 1000, Color.ALICEBLUE);
		controller.setKeys(playScene);
		primaryStage.setScene(playScene);
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
				
				// Needs to be fixed, aka a better system of spawning fish
				populateEnemies();
				time = 0;	
			
				player.updateLocation(x * player.getSpeed(), y * player.getSpeed());
				updateFish(); // called everytime? might be a problem
				updateScore();
			}
			
		};
		
		at.start();
		
	}
	
	private void updateScore() {
		scoreLabel.setText("Score: " + Integer.toString(score));
	}
	
	private void updateFish() {
		for(Fish fish : new ArrayList<Fish>(enemies)) {
			//handles all movement of fish.. and need a better way of handling initial 0s
			if(fish.getLocationX() != 0 && checkCollision(fish)) {
				//TODO: also note that removing the fish, removes the first INSTANCE of the fish
				//so will need to fix
				if(checkSize(fish)) {
					removeFish(fish);
				} else {
					removeFish(player);
					gameOver();
				}
			}			
		}
	}

	//TODO: FIX CODE
	//I could make this a one line code if needed but it would be really long
	private boolean checkCollision(Fish fish) {
		double minX = fish.getLocationX();
		double maxX = fish.getLocationX() + fish.getWidth();
		double minY = fish.getLocationY();
		double maxY = fish.getLocationY() + fish.getHeight();
		
		if(player.getLocationX() >= minX && player.getLocationX() < maxX) {
			if(player.getLocationY() >= minY && player.getLocationY() < maxY) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkSize(Fish fish) {
		//System.out.println("Fish : " + fish.getSize() + " Player:" + player.getSize());
		return fish.getSize() <= player.getSize();
	}
	
	//produce on enum type
	private static Fish createFish(FishType type) {
		switch(type) {
			case SHARK:
				return new Shark(100, 200);
		}
		return null;
	}

	private void addFish(Fish fish) {
		enemies.add(fish);
		root.getChildren().add(fish);
	}
	
	private void removeFish(Fish fish) {
		score += fish.getScore();
		root.getChildren().remove(fish);
		enemies.remove(fish);
	}
	
	public void gameOver() {
		root.getChildren().clear();
		enemies.clear();
		StackPane end = new StackPane();
		Label endLabel = new Label("Game Over!");
		end.getChildren().add(endLabel);
		endScene = new Scene(end, 1000, 1000, Color.ALICEBLUE);
		primaryStage.setScene(endScene);
	}
	
	private void populateEnemies() {
		if(sharka) {
			Fish shark = new Shark(200, 200);
			Fish puffer = new Pufferfish();
			addFish(shark);
			addFish(puffer);
			sharka = false;
		}
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
}
