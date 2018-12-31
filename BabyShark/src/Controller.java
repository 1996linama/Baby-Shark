import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
	public boolean moveUp, moveDown, moveRight, moveLeft;
	
	public Controller() {}
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

}
