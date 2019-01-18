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
	//private static StackPane root;
	private Score scoreLabel = new Score(0);
	private static Player player;
	private AnimationTimer at;

	private static int currentLevel = 0;
	private Controller controller;	
	private EnemyController enemyController;

	ImageView background = new ImageView(
			new Image(getClass().getResourceAsStream("/res/background.png"), 800, 800, true, true));

	private final static int[] levels = {0, 20, 40, 70, 120, 200, 250, 300, 400};
	
	public Game(Parent primary) {
		super(primary);
		setScene();

	}
	
	private void operate() {
		at = new AnimationTimer() {
			@Override
			public void handle(long time) {
					
				controller.move(); //moves the player
				checkPlayerBounds(); // checks player
				enemyController.populateEnemies(); // populates the screen with enemies
				enemyController.updateFish(); // updates the fish
				updateScore(); // updates the score
				setLevel();
				if(player.isVisible()) {
				//	gameOver("In Russia, fish eats YOU!");
				}
			}
		};
		
		at.start();
		
	}
	
	public void setScene() {
		loadObjects();
		setGameWindow();
		loadMusic();
		controller.setKeys(this);
		operate();
		
	}
	
	private void updateScore() {
		scoreLabel.setScore(player.getScore());
	}
	
	private void loadObjects() {
		controller = new Controller();
		enemyController = new EnemyController();
		player = new Player();
	}
	
	// change this
	public static Player getPlayer() {
		return player;
	}

	private void setGameWindow() {
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
	
	public static int getLevel() {
		return currentLevel;
	}
	
	public static int getLevelNum() {
		return levels[currentLevel];
	}
	
	public void setLevel() {
		for(int i = 0; i < levels.length; i++) {
			if(scoreLabel.getScore() >= levels[i] && scoreLabel.getScore() <= levels[i + 1]) {
				currentLevel = i;
			}
		}
	}

	public Scene getPlayScene() {
		return this;
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
