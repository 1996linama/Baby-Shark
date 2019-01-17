
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Game extends Application {
	private Stage primaryStage;
	private Frame frame;
	private Scene playScene;
	private Scene endScene;
	private Score scoreLabel = new Score(0);
	public static Player player;
	private static StackPane root;
	private BorderPane border;
	private HBox topMenu;
	private Controller controller;
	private EnemyController enemyController;
	private  Random random = new Random();;
	private AnimationTimer at;
	double x = 0;
	double y = 0;

	ImageView background = new ImageView(
			new Image(getClass().getResourceAsStream("/res/background.png"), 800, 800, true, true));

//	List<Fish> enemies = new ArrayList<Fish>();
//	List<FishType> types = Arrays.asList(FishType.values());

	private void initialize() {
		frame = new Frame(primaryStage);
		controller = new Controller();
		enemyController = new EnemyController();
		player = new Player();
	}
	
	
	private void loadObjects() {
		
	}
	
	
	public static Player getPlayer() {
		return player;
	}

	private void initMusic() {
		Media media = new Media(getClass().getResource("/res/bgm.mp3").toString());
		MediaPlayer music = new MediaPlayer(media);
		music.setAutoPlay(true);
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.play();
	}
	
	private void setGameWindow() {
		root = new StackPane();
		border = new BorderPane();
		topMenu = new HBox();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		setGameWindow();
		initialize();
		initMusic();
		
		topMenu.getChildren().add(scoreLabel);
		border.setTop(topMenu);
		
		addAllToScreen(background, player, topMenu);

		playScene = new Scene(root);
		controller.setKeys(playScene);
		primaryStage.setScene(playScene);
		primaryStage.show();
		

		at = new AnimationTimer() {
			@Override
			public void handle(long time) {

				controller.move(); //moves the player
				checkPlayerBounds(); // checks player
				enemyController.populateEnemies(); // populates the screen with enemies
				enemyController.updateFish(); // updates the fish
				updateScore(); // updates the score
					
				if(!Game.player.isVisible()) {
					gameOver("In Russia, fish eats YOU!");
				}
			}
		};
		at.start();

	}

	private void checkPlayerBounds() {
		if (player.getLocationX() > Frame.getMaxX() - player.getWidth() / 2) {
		}
		if (player.getLocationX() < Frame.getMinX() + player.getWidth() / 2) {
		}
		if (player.getLocationY() > Frame.getMaxY() - player.getHeight() / 2) {
		}
		if (player.getLocationY() < Frame.getMinY() + player.getHeight() / 2) {
		}
	}

	private void updateScore() {
		scoreLabel.setScore(player.getScore());
	}

	public static void addAllToScreen(Node ... node) {
		root.getChildren().addAll(node);
	}
	
	public static void addToScreen(Node node) {
		root.getChildren().add(node);
	}
	
	public static void removeFromScreen(Node node) {
		root.getChildren().remove(node);
	}

	/* Will Move This */
	public void gameOver(String why) {
		at.stop();
		player = new Player();
		//enemies.clear();
		root.getChildren().clear();
		VBox end = new VBox(12);
		Label endLabel = new Label("Game Over!");
		end.getChildren().add(endLabel);
		end.getChildren().add(new Label(why));
		Button restart = new Button("Play Again");
		restart.setOnAction(e -> {
			try {
				start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		end.getChildren().add(restart);
		endScene = new Scene(end, 1000, 1000, Color.ALICEBLUE);
		primaryStage.setScene(endScene);
	}



	/*****************************************
	 * Window Controller
	 *************************************/

	public static void main(String[] args) {
		Application.launch(args);
	}

}
