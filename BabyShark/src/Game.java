
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
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
	private ScoreLabel scoreLabel = new ScoreLabel(0);
	private Player player;
	private StackPane root;
	private BorderPane border;
	private HBox topMenu;
	private Controller controller;
	private static Random random = new Random();;
	private AnimationTimer at;
	double x = 0;
	double y = 0;

	ImageView background = new ImageView(
			new Image(getClass().getResourceAsStream("/res/background.jpg"), 800, 800, true, true));

	List<Fish> enemies = new ArrayList<Fish>();
	List<FishType> types = Arrays.asList(FishType.values());

	private void initialize() {
		frame = new Frame(primaryStage);
		controller = new Controller();
		player = new Player();
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
		this.primaryStage = primaryStage;
		root = new StackPane();
		border = new BorderPane();
		topMenu = new HBox();

		initialize();
		initMusic();

		topMenu.getChildren().add(scoreLabel);
		border.setTop(topMenu);

		root.getChildren().add(background);
		root.getChildren().add(player);
		root.getChildren().add(border);

		playScene = new Scene(root);
		controller.setKeys(playScene);
		primaryStage.setScene(playScene);
		primaryStage.show();

		at = new AnimationTimer() {
			@Override
			public void handle(long time) {

				if (controller.moveUp) {
					y -= 1;
				}
				if (controller.moveDown) {
					y += 1;
				}
				if (controller.moveRight) {
					x += 1;
					player.flipRight();

				}
				if (controller.moveLeft) {
					x -= 1;
					player.flipLeft();
				}

				player.updateLocation(x * player.getSpeed(), y * player.getSpeed());
				checkPlayerBounds();
				updateFish();
				updateScore();
				populateEnemies();
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
	
	private void checkFishBounds(Fish fish) {
		if (fish.isReversed && fish.getLocationX() > 480.0) {
			removeFish(fish);
		}
		
		if (fish.isReversed && fish.getLocationX() < -480.0) {
			removeFish(fish);
		}
	}

	private void updateScore() {
		scoreLabel.setScore(player.getScore());
	}

	private void updateFish() {
		for (Fish fish : new ArrayList<Fish>(enemies)) {
			if (player.getX() != 0 && isColliding(fish, player)) {
				if (canPlayerEatEnemy(fish)) {
					player.addScore(fish.getScore());
					removeFish(fish);
				} else {
					removeFish(player);
					gameOver("In Russia, fish eats YOU!");
				}

			}

			// when fish is off screen
			checkFishBounds(fish);
		}
	}

	private boolean canPlayerEatEnemy(Fish fish) {
		return fish.getSize() <= player.getSize();
	}
	
	private Fish createFish() {
		int rand = random.nextInt(types.size());
		return new EnemyFish(types.get(rand));
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

	private void gameOver(String why) {
		at.stop();
		player = new Player();
		enemies.clear();
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

	private void populateEnemies() {
		
		while (enemies.size() < 8) {
			addFish(createFish());
		}
	}

	private boolean isColliding(Fish fishOne, Fish fishTwo) {
		return fishOne.getBoundsInParent().intersects(fishTwo.getBoundsInParent());
	}

	/*****************************************
	 * Window Controller
	 *************************************/

	public static void main(String[] args) {
		Application.launch(args);
	}

}
