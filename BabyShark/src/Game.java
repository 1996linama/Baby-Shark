
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
	private Stage primaryStage;
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
	private static Random random = new Random();
	private int score = 0;
	
	double x = 0;
	double y = 0;

	ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/res/background.jpg"), 900, 900, true, true));
	ArrayList<Fish> enemies = new ArrayList<Fish>(); //stores the fish that exist on screen
	ArrayList<FishType> types = new ArrayList<FishType>(Arrays.asList(FishType.values()));
	
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
		
		scoreLabel = new Label("Score: " + score);
		scoreLabel.setFont(new Font("Arial", 18.0));
		scoreLabel.setAlignment(Pos.TOP_LEFT); 
		scoreLabel.setTextAlignment(TextAlignment.LEFT);	
		initialize();
		topMenu.getChildren().add(scoreLabel);
		border.setTop(topMenu);
		
		Media media = new Media(getClass().getResource("/res/bgm.mp3").toString());
		MediaPlayer music = new MediaPlayer(media);
		music.setAutoPlay(true);
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.play();
		 
		root.getChildren().add(background);
		root.getChildren().add(player);
		root.getChildren().add(border);
		
		playScene = new Scene(root, 800, 600, Color.ALICEBLUE);
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
					player.flipRight();

				}
				if(controller.moveLeft) { 
					x -= 1;
					player.flipLeft();
				}

				populateEnemies();	
				player.updateLocation(x * player.getSpeed(), y * player.getSpeed());
				updateFish();
				updateScore();
			}
			
		};
		
		at.start();
		
	}
	
	private void updateScore() {
		scoreLabel.setText("Score: " + score);
	}
	
	private void updateFish() {
		for(Fish fish : new ArrayList<Fish>(enemies)) {
			if(checkCollisions(fish)) {
				if(checkSize(fish)) {
					removeFish(fish);
				} else {
					removeFish(player);
					gameOver();
				}
				
			}	
			
			if(!fish.isAlive()) {
				removeFish(fish);
			}
		}
	}

	private boolean checkCollisions(Fish fish) {
		return fish.getBoundsInParent().intersects(player.getBoundsInParent());
	}
	
	private boolean checkSize(Fish fish) {
		return fish.getSize() <= player.getSize();
	}
	
	//produce on enum type
	private Fish createFish(FishType type) {
		switch(type) {
			case SHARK:
				return new Shark();
			case PUFFERFISH:
				return new Pufferfish();
			case CATFISH:
				return new Catfish();
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
		score += fish.getScore();
		fish.setLife();
		root.getChildren().remove(fish);
		enemies.remove(fish);
	}
	
	private void setInstructions() {
		StackPane instruct = new StackPane();
		Label instructLabel = new Label("Instructions");
		instruct.getChildren().add(instructLabel);
		instructScene = new Scene(instruct, 800, 600, Color.ALICEBLUE);
		primaryStage.setScene(instructScene);
	}
	
	private void gameOver() {
		enemies.clear();
		root.getChildren().clear();
		VBox end = new VBox(12);
		Label endLabel = new Label("Game Over!");
		end.getChildren().add(endLabel);
		Button restart = new Button("Play Again");
		restart.setOnAction(e -> primaryStage.setScene(playScene));
		end.getChildren().add(restart);
		endScene = new Scene(end, 1000, 1000, Color.ALICEBLUE);
		primaryStage.setScene(endScene);
	}
	
	private void populateEnemies() {
		
		for(int i = enemies.size(); i < 10; i++) {
			//randomize the enumerations
			Fish temp = createFish(randomFishType());
			addFish(temp);
		}
		
	}
	
	/*****************************************Window Controller*************************************/
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
}
