import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameOver extends Scene {

	private VBox end;
	private StackPane root;
	private Label gameOver;
	
	public GameOver(StackPane primary) {
		super(primary);
		root = primary;
		setGameOverLayout();
	}

	private void setGameOverLayout() {
		end = new VBox(12);
		gameOver = new Label("Game Over!");
		end.getChildren().add(gameOver);
		Button restart = new Button("Play Again");
		
		restart.setOnAction(e -> {
			try {
				BabyShark.setGame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		end.getChildren().add(restart);
		root.getChildren().add(end);

	}
}



