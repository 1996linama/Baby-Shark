import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BabyShark extends Application {
	private static Stage primaryStage;
	public Frame frame;
	private static Game game;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		frame = new Frame(primaryStage);
		try {
			setMenu();		
			primaryStage.show();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setGame() {
		game = new Game(new StackPane());
		primaryStage.setScene(game);
	}
	
	public static void setGameOver() {
		primaryStage.setScene(new GameOver(new StackPane()));	
	}
		
	public static void setMenu() {
		primaryStage.setScene(new MainMenu(new StackPane()));
	}
	
	public static void setScene(Scene scene) {
		primaryStage.setScene(scene);
	}
		
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
