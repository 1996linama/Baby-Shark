import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class MainMenu extends Scene {
	
	private VBox menuOptions;
	private BorderPane border;
	
	public MainMenu(Parent primary) {
	
		super(primary);

		menuOptions = new VBox(20);
		border = new BorderPane();
		
		Button play = new Button("Start");
		play.setOnAction(e -> {
			try {
				BabyShark.setPlayScene();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		Button instruct = new Button("How to Play");
		play.setOnAction(e -> {
			try {
			//instructions
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
		
		menuOptions.getChildren().addAll(play, quit);
		border.setCenter(menuOptions);
		
		BabyShark.root2.getChildren().add(border);
		
	}
}

