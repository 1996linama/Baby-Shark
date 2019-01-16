
import java.io.File;
import java.util.*;

import javafx.scene.media.Media;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.*;


public class Game extends Application {
	private Stage gameWindow;
	private Stage menuStage;
	private Frame frame;
	private Scene playScene;
	private Scene endScene;
	private Scene instructScene;
	private Label scoreLabel;
	private Player player;
	private StackPane root;
	private BorderPane border;
	private HBox topMenu;
	private Controller controller;
	private static Random random = new Random();;
	
	double x = 0;
	double y = 0;

	ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/res/background.jpg"), 900, 900, true, true));

	ArrayList<Fish> enemies = new ArrayList<Fish>(); //stores the fish that exist on screen
	ArrayList<FishType> types = new ArrayList<FishType>(Arrays.asList(FishType.values()));
	
	private void initialize() {
		frame = new Frame(gameWindow);
		controller = new Controller();
		player = new Player();
	}
	
	private void initScore() {
		scoreLabel = new Label("Score: " + player.getScore());
		scoreLabel.setFont(new Font("Arial", 18.0));
		scoreLabel.setAlignment(Pos.TOP_LEFT); 
		scoreLabel.setTextAlignment(TextAlignment.LEFT);
	}
	
	private void initMusic() {
		Media media = new Media(getClass().getResource("/res/bgm.mp3").toString());
		MediaPlayer music = new MediaPlayer(media);
		music.setAutoPlay(true);
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.play();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.gameWindow = primaryStage;
		root = new StackPane();
		border = new BorderPane();
		topMenu = new HBox();
			
		initialize();
		initScore();
		initMusic();
		
		topMenu.getChildren().add(scoreLabel);
		border.setTop(topMenu);
		
		root.getChildren().add(background);
		root.getChildren().add(player);
		root.getChildren().add(border);
		
		playScene = new Scene(root);
		controller.setKeys(playScene);
		gameWindow.setScene(playScene);
		gameWindow.show();
		
		//handles controller movement. 
		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long time) {

				if(controller.moveUp) { y -= 1; }
				if(controller.moveDown) { y += 1; }		
				if(controller.moveRight) { 
					x += 1;
					player.flipRight();

				}
				if(controller.moveLeft) { 
					x -= 1;
					player.flipLeft();
				}

				player.updateLocation(x * player.getSpeed(), y * player.getSpeed());
				
				populateEnemies();	
				
				checkPlayerBounds();
				
				updateFish();
				
				updateScore();
			}
			
		};
		
		at.start();
		
	}
	
	private void checkPlayerBounds() {
		if(player.getLocationX() > 400.0 + player.getWidth()) {
		}
		
		if(player.getLocationX() < -400.0 - player.getWidth()) {
		}

		if(player.getLocationY() > 400.0 + player.getHeight()) {
		}
		
		if(player.getLocationY() < -400.0 - player.getHeight()) {
		}
		
	}
	
	private void updateScore() {
		scoreLabel.setText("Score: " + player.getScore());
	}
	
	private void updateFish() {
		for(Fish fish : new ArrayList<Fish>(enemies)) {
			if(player.getLocationX() != 0 && hasCollided(fish, player)) {
				if(checkSize(fish)) {
					player.addScore(fish.getScore());
					removeFish(fish);
				} else {
					removeFish(player);
					gameOver();
				}
				
			}	

			/* remove fish if outside screen */
			if(fish.getLocationX() > 500.0) {
				removeFish(fish);
			}
		}
	}

	// checks for collision of two fishes
	private boolean hasCollided(Fish fishOne, Fish fishTwo) {
		return fishOne.getBoundsInParent().intersects(fishTwo.getBoundsInParent());
	}
	
	// compares the size to see if the enemy fish can be eaten
	private boolean checkSize(Fish fish) {
		return fish.getSize() <= player.getSize();
	}
	
	//produce on enum type on random
	private Fish createFish(FishType type) {
		switch(type) {
			case SHARK:
				return new Shark();
			case PUFFERFISH:
				return new Pufferfish();
			case CATFISH:
				return new Catfish();
			case TUNA:
				return new Tuna();
			case GUPPY:
				return new Guppy();
		default:
			break;
		}
		return null;
	}

	
	private FishType randomFishType() {
		int rand = random.nextInt(types.size());
		return types.get(rand);
	}

	private void addFish(Fish fish) {
		enemies.add(fish);
		root.getChildren().add(fish);
	}
	
	
	private void removeFish(Fish fish) {
		fish.kill();
		root.getChildren().remove(fish);
		enemies.remove(fish);
	}
	
	private void clear() {
		enemies.clear();
		root.getChildren().clear();
	}
	
	private void setInstructions() {
		StackPane instruct = new StackPane();
		Label instructLabel = new Label("Instructions");
		instruct.getChildren().add(instructLabel);
		instructScene = new Scene(instruct, 800, 600, Color.ALICEBLUE);
		gameWindow.setScene(instructScene);
	}
	
	private void gameOver() {
		clear();
		VBox end = new VBox(12);
		Label endLabel = new Label("Game Over!");
		end.getChildren().add(endLabel);
		Button restart = new Button("Play Again");
		restart.setOnAction(e -> gameWindow.setScene(playScene));
		end.getChildren().add(restart);
		endScene = new Scene(end, 1000, 1000, Color.ALICEBLUE);
		gameWindow.setScene(endScene);
	}
	
	private void populateEnemies() {
		
		if(enemies.isEmpty()) {
			Fish temp = createFish(randomFishType());
			addFish(temp);
		}
		
		int numOfEnemies = 8;
		/* naive and terrible solution to preventing overlaps in the spawning system*/
		for(int i = enemies.size(); i < numOfEnemies; i++) {
			boolean check = false;
			Fish temp = null;
			for(Fish fish: enemies) {
				temp = createFish(randomFishType());
				if(!hasCollided(fish, temp)) {
					check = true;
				} else {
					check = false;
				}
			}
			
			if(check && temp != null) {
				addFish(temp);
			}
		}
		
	}
	
	/*****************************************Window Controller*************************************/
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
}
