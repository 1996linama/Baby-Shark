import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
import javafx.stage.Stage;

public class BabyShark extends Application {
	private static Stage primaryStage;
	private Frame frame;
	private static Scene playScene;
	private static Scene mainMenu;
	private Scene endScene;
	private static Scene currScene;
	
	public static StackPane root2; //TODO: will rename
	public static StackPane root;

	private void initializeWindow() {
		frame = new Frame(primaryStage);
		root = new StackPane();
		root2 = new StackPane();
		playScene = new Game(root); // GameWindow is a scene!!
		mainMenu = new MainMenu(root2);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		initializeWindow();
		primaryStage.setScene(mainMenu);		
		primaryStage.show();
	
	}
	
	public static Stage getStage() {
		return primaryStage;
	}

	
	public static void setPlayScene() {
		primaryStage.setScene(playScene);
	}
	
	public static Scene getPlayScene() {
		return playScene;
	}
	
	public static void setMenu() {
		primaryStage.setScene(mainMenu);
	}
	
	public static Scene getMenu() {
		return mainMenu;
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
	/*
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
	}*/

	public static void main(String[] args) {
		Application.launch(args);
	}

}
