import javafx.application.Application;
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
		} catch (Exception io) {
			System.exit(-1);
		}
	}
	
	public static void setGame() {
		game = newGame();
		primaryStage.setScene(game);
	}
	
	public static void setGameOver() {
		primaryStage.setScene(new GameOver(new StackPane()));	
	}
		
	public static void setMenu() {
		primaryStage.setScene(new MainMenu(new StackPane()));
	}
	
	public static Game newGame() {
		return new Game(new StackPane());
	}
	
	public static void createNewGame() {
		game = new Game(new StackPane());
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
