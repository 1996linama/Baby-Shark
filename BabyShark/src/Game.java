import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game extends Scene {

	//layout objects
	private static StackPane root;
	private BorderPane border;
	private HBox topMenu;
	private static Score scoreLabel = new Score(0);
	
	// Game Objects
	private LevelGenerator levelGenerator;
	private static Player player;
	public static AnimationTimer timer;
	private FishController fishController;

	// Game Info
	private static Level currentLevel;
	private static int score;
	
	ImageView background = new ImageView(
			new Image(
					getClass().getResourceAsStream("/res/gamebg.png"), 
					800, 600, true, true));
	MediaPlayer music = new MediaPlayer(
			new Media(getClass().getResource("/res/bgm.mp3").toString()));
	
	public Game(StackPane primary) {
		super(primary);
		root = primary;
		loadObjects();
		setGameLayout();
		loadMusic();
		Controller.setKeys(this);
		loopGame();
	}
	
	private void loadObjects() {
		fishController = new FishController();
		player = new Player();
		levelGenerator = new LevelGenerator();
	}

	private void setGameLayout() {
		border = new BorderPane();
		topMenu = new HBox();
		topMenu.getChildren().add(scoreLabel);
		border.setTop(topMenu);
		root.getChildren().addAll(background, player, topMenu);	
	}
	
	private void loadMusic() {
		music.setAutoPlay(true);
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.play();
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static Level getCurrentLevel() {
		return currentLevel;
	}
	
	public static void setCurrentLevel(Level level) {
		currentLevel = level;
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void updateScoreLabel() {
		scoreLabel.setScore(score);
	}

	public static void setScore(int value) {
		score += value;
	}
	
	public static void add(Node node) {
		root.getChildren().add(node);
	}
	
	public static void remove(Node node) {
		root.getChildren().remove(node);
	}
	
	private void loopGame() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				
				for (EnemyFish fish : new ArrayList<EnemyFish>(fishController.getEnemies())) {
					if (player.getX() != 0 && fish.isColliding(player)) {
						if (player.canPlayerEatEnemy(fish)) {
							setScore(fish.getFishValue());
							updateScoreLabel(); // updates the score
							fishController.removeFish(fish);
							remove(fish);
						} else {
							BabyShark.setGameOver();
						}
					}
					
					if(!fish.isVisible()) {
						fishController.removeFish(fish);
						remove(fish);
					}
				}
					
				fishController.setNumOfEnemies(currentLevel.getNumOfEnemies());
				fishController.populateEnemies(); // populates the screen with enemies
				Controller.move(); //moves the player
				checkPlayerBounds(); // checks player
				updateScoreLabel(); // updates the score
				LevelGenerator.changeLevel(score); //updateLevel
			}
		};
		
		timer.start();
		
	}
	
	private void checkPlayerBounds() {
		if (player.getLocationX() > Frame.getMaxX() + player.getWidth()) {
			Controller.x = Frame.getMinX() - player.getWidth();
		} else if (player.getLocationX() < Frame.getMinX() - player.getWidth()) {
			Controller.x = Frame.getMaxX() + player.getWidth();
		} else if (player.getLocationY() > Frame.getMaxY() - player.getHeight()) {
			Controller.y = Frame.getMaxY() - player.getHeight();
		} else if (player.getLocationY() < Frame.getMinY() + player.getHeight()) {
			Controller.y = Frame.getMinY() + player.getHeight();
		} 
	}

}
