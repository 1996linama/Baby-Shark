import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameOver extends Scene {

	private VBox end;
	private StackPane root;
	private Label gameOver;
	
	public GameOver(StackPane root) {
		super(root);
		this.root = root;
		setGameOverLayout();
	}

	private void setGameOverLayout() {
		this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
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
		
		Button quit = new Button("Quit");
		
		quit.setOnAction(e -> {
			try {
				System.exit(0);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		end.getChildren().addAll(restart, quit);
		root.getChildren().add(end);

	}
}



