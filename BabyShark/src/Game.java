import java.util.*;

import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.*;


public class Game extends Application {

	private Scene scene;
	private Player player;
	private MediaPlayer music;
	ImageView background = new ImageView(new Image(getClass().getResourceAsStream("/res/background.jpg"), 900, 900, true, true));

	ArrayList<Fish> enemies = new ArrayList<Fish>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Frame frame = new Frame(primaryStage);
		StackPane root = new StackPane();
		Label score = new Label();
		score.setAlignment(Pos.TOP_RIGHT); 
		score.setTextAlignment(TextAlignment.CENTER);
		scene = new Scene(root, 800, 600, Color.ALICEBLUE);
		
		Controller controller = new Controller();
		player = new Player(controller);
		controller.setKeys(scene);

		root.getChildren().add(background);
		root.getChildren().add(player);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long time) {
				double x = player.getX();
				double y = player.getY();
				
				if(controller.moveUp) { y -= 1; }
				if(controller.moveDown) { y += 1; }
				if(controller.moveRight) { x += 1; }
				if(controller.moveLeft) { y -= 1; }
				
			}
		
		};
		
		at.start();

	}
	
	private void populateEnemies() {
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
}
