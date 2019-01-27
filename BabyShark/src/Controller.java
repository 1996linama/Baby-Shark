import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
	public static boolean moveUp;

	public static boolean moveDown;

	public static boolean moveRight;

	public static boolean moveLeft;

	static double x = 0;
	static double y = 0;
	
	public Controller() {}
	
	public static void move() {
		if (moveUp) {
			y -= Game.getPlayer().getSpeed();
		}
		if (moveDown) {
			y += Game.getPlayer().getSpeed();
		}
		if (moveRight) {
			x += Game.getPlayer().getSpeed();
			Game.getPlayer().flipRight();
		}
		if (moveLeft) {
			x -= Game.getPlayer().getSpeed();
			Game.getPlayer().flipLeft();
		}
		
		Game.getPlayer().updateLocation(x, y);
	}

	/* Handles key movements for the player, may move this to Game.java */
	public static void setKeys(Scene scene) {
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
