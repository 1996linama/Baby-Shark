/*
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.animation.*;

*/

import java.util.*;

import javax.swing.JPanel;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Label;



public class Game extends Application {

	private Scene scene;
	boolean moveUp, moveDown, moveRight, moveLeft;
	private Player player = new Player();
	
	public void setKeys(Scene scene) {
		EventHandler<KeyEvent> k = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if(key.getCode() == KeyCode.UP) {
					moveUp = true;
				} else {
					moveUp = false;
				}
				
				if(key.getCode() == KeyCode.DOWN) {
					moveDown = true;
				} else {
					moveDown = false;
				}
				
				if(key.getCode() == KeyCode.RIGHT) {
					moveRight = true;
				} else {
					moveRight = false;
				}
				
				if(key.getCode() == KeyCode.LEFT) {
					moveLeft = true;
				} else {
					moveLeft = false;
				}
			}
		};
		
		scene.setOnKeyPressed(k);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Frame frame = new Frame(primaryStage);
		StackPane root = new StackPane();
		Label score = new Label();
		//score.setAlignment(TOP_RIGHT); 
		score.setTextAlignment(TextAlignment.CENTER);
		scene = new Scene(root, 800, 600, Color.ALICEBLUE);
		setKeys(scene);
		root.getChildren();
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	
}
