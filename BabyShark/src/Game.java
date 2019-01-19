import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Game extends Scene {

	private BorderPane border;
	private HBox topMenu;
	private Score scoreLabel = new Score(0);
	private LevelGenerator levelGenerator;
	private static Levels currentLevel; // currentLevel will affect player
	private static Player player;
	private AnimationTimer timer;
	private Controller controller;	
	private FishController fishController;

	ImageView background = new ImageView(
			new Image(getClass().getResourceAsStream("/res/gamebg.png"), 800, 600, true, true));
	
	public Game(Parent primary) {
		super(primary);
		setScene();
		play();

	}
	
	private void play() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
					
				controller.move(); //moves the player
				checkPlayerBounds(); // checks player
				fishController.populateEnemies(); // populates the screen with enemies
				fishController.updateFish(); // updates the fish
				updateScore(); // updates the score
				if(player.isVisible()) {
				//	gameOver("In Russia, fish eats YOU!");
				}
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
		addAllToScreen(background, player, topMenu);	
	}
	
	private void loadMusic() {
		Media media = new Media(getClass().getResource("/res/bgm.mp3").toString());
		MediaPlayer music = new MediaPlayer(media);
		music.setAutoPlay(true);
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.play();
	}
	
	//new generator based
	public static Levels getCurrentLevels() {
		return currentLevel;
		
	}
	
	public static void setCurrentLevels(Levels level) {
		currentLevel = level;
	}

	//enum based
	public static Level getCurrLevel() {
		return Level.getLevel(Score.getScore());
	}
	
	public static int getScore() {
		return Score.getScore();
	}
	
	public static void addAllToScreen(Node ... node) {
		BabyShark.root.getChildren().addAll(node);
	}
	
	public static void addToScreen(Node node) {
		BabyShark.root.getChildren().add(node);
	}
	
	public static void removeFromScreen(Node node) {
		BabyShark.root.getChildren().remove(node);
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
