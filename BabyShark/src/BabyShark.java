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
	private static Scene game;
	private static Scene mainMenu;
	private static Scene endScene;
	
	public static StackPane root; //TODO: will rename ALL 
	public static StackPane root2;
	public static StackPane root3;

	private void initializeWindow() {
		frame = new Frame(primaryStage);
		root = new StackPane();
		root2 = new StackPane();
		root3 = new StackPane();
		game = new Game(root);
		mainMenu = new MainMenu(root2);
		endScene = new GameOver(root3);
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
		BabyShark.getStage().setScene(game);
	}
	
	public static Scene getPlayScene() {
		return game;
	}
	
	public static void setMenu() {
		BabyShark.getStage().setScene(mainMenu);
	}
	
	public static Scene getMenu() {
		return mainMenu;
	}
	
	public static void setGameOver() {
		BabyShark.getStage().setScene(endScene);
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
