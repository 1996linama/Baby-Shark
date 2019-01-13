import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Controller {
	public boolean moveUp, moveDown, moveRight, moveLeft;
	
	public Controller() {
		
	}
	
	/* Handles key movements for the player, may move this to Game.java */	
	public void setKeys(Scene scene) {
		EventHandler<KeyEvent> p = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if(key.getCode() == KeyCode.UP) {
					moveUp = true;
				}
				
				if(key.getCode() == KeyCode.DOWN) {
					moveDown = true;
				}
				
				if(key.getCode() == KeyCode.RIGHT) {
					moveRight = true;
				}
				
				if(key.getCode() == KeyCode.LEFT) {
					moveLeft = true;
				} 
			}
		};
		
		
		EventHandler<KeyEvent> r = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if(key.getCode() == KeyCode.UP) {
					moveUp = false;
				}
				
				if(key.getCode() == KeyCode.DOWN) {
					moveDown = false;
				}
				
				if(key.getCode() == KeyCode.RIGHT) {
					moveRight = false;
				}
				
				if(key.getCode() == KeyCode.LEFT) {
					moveLeft = false;
				}
			}
		};
		
		scene.setOnKeyPressed(p);
		scene.setOnKeyReleased(r);
		
	}

}
