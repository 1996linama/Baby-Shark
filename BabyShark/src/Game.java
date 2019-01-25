import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Scene {

	//layout objects
	private BorderPane border;
	private HBox topMenu;
	private Score scoreLabel = new Score(0);
	private static StackPane root;
	
	
	// Game Objects
	private LevelGenerator levelGenerator;
	private static Player player;
	public static AnimationTimer timer;
	private Controller controller;	
	private FishController fishController;

	// Game Info
	private static Levels currentLevel; // currentLevel will affect player
	
	
	ImageView background = new ImageView(
			new Image(getClass().getResourceAsStream("/res/gamebg.png"), 800, 600, true, true));
	
	public Game(StackPane primary) {
		super(primary);
		root = primary;
		setScene();
		play();
	}
	
	private void play() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				
				if(!player.isVisible()) {
					timer.stop();
					BabyShark.setGameOver();
				}
				
				controller.move(); //moves the player
				checkPlayerBounds(); // checks player
				fishController.populateEnemies(); // populates the screen with enemies
				fishController.updateFish(); // updates the fish
				updateScore(); // updates the score
				LevelGenerator.changeLevels(Game.getScore());
			}
		};
		
		timer.start();
		
	}
	
	private void setScene() {
		loadObjects();
		setGameLayout();
		loadMusic();
		controller.setKeys(this);
	}
	
	private void updateScore() {
		scoreLabel.setScore(player.getScore());
	}
	
	private void loadObjects() {
		controller = new Controller();
		fishController = new FishController();
		player = new Player();
		levelGenerator = new LevelGenerator();
	}
	
	// change this
	public static Player getPlayer() {
		return player;
	}

	private void setGameLayout() {
		border = new BorderPane();
		topMenu = new HBox();
		topMenu.getChildren().add(scoreLabel);
		border.setTop(topMenu);
		root.getChildren().addAll(background, player, topMenu);	
	}
	
	private void loadMusic() {
		MediaPlayer music = new MediaPlayer(new Media(getClass().getResource("/res/bgm.mp3").toString()));
		music.setAutoPlay(true);
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.play();
	}
	
	public static Levels getCurrentLevels() {
		return currentLevel;
	}
	
	public static void setCurrentLevels(Levels level) {
		currentLevel = level;
	}
	
	public static int getScore() {
		return Score.getScore();
	}
	
	public static void add(Node node) {
		root.getChildren().add(node);
	}
	

	public static void remove(Node node) {
		root.getChildren().remove(node);
	}
	
	private void checkPlayerBounds() {
		double locationX = player.getLocationX();
		double locationY = player.getLocationY();
		if (locationX > Frame.getMaxX() + player.getWidth()) {
			controller.x = Frame.getMinX() - player.getWidth();
		}
		if (locationX < Frame.getMinX() - player.getWidth()) {
			controller.x = Frame.getMaxX() + player.getWidth();
		}
		if (locationY > Frame.getMaxY() - player.getHeight()) {
			controller.y = Frame.getMaxY() - player.getHeight();
		}
		if (locationY < Frame.getMinY() + player.getHeight()) {
			controller.y = Frame.getMinY() + player.getHeight();
		}
	}

}
