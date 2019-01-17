import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
	public boolean moveUp, moveDown, moveRight, moveLeft;

	double x = 0;
	double y = 0;
	
	public Controller() {}
	
	public void move() {
		if (moveUp) {
			y -= 1;
		}
		if (moveDown) {
			y += 1;
		}
		if (moveRight) {
			x += 1;
			Game.getPlayer().flipRight();

		}
		if (moveLeft) {
			x -= 1;
			Game.getPlayer().flipLeft();
		}

		Game.getPlayer().updateLocation(x * Game.getPlayer().getSpeed(), y * Game.getPlayer().getSpeed());
	}

	/* Handles key movements for the player, may move this to Game.java */
	public void setKeys(Scene scene) {
		EventHandler<KeyEvent> onKeyPressed = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				switch (key.getCode()) {
				case UP:
					moveUp = true;
					break;
				case DOWN:
					moveDown = true;
					break;
				case RIGHT:
					moveRight = true;
					break;
				case LEFT:
					moveLeft = true;
					break;
				}
				return;
			}
		};

		EventHandler<KeyEvent> onKeyReleased = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				switch (key.getCode()) {
				case UP:
					moveUp = false;
					break;
				case DOWN:
					moveDown = false;
					break;
				case RIGHT:
					moveRight = false;
					break;
				case LEFT:
					moveLeft = false;
					break;
				}
				return;
			}
		};

		scene.setOnKeyPressed(onKeyPressed);
		scene.setOnKeyReleased(onKeyReleased);

	}

}
