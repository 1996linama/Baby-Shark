import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MainMenu extends Scene {
	
	private VBox menuOptions;
	private BorderPane border;
	private StackPane root;
	private Button play, instruct, quit;
	ImageView background = new ImageView(
			new Image(getClass().getResourceAsStream("/res/menubg.png"), 800, 800, true, true));
	
	public MainMenu(StackPane primary) {
	
		super(primary);
		root = primary;
		
		this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		initialize();

		play.setOnAction(e -> {
			try {
				BabyShark.setGame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		instruct.setOnAction(e -> {
			try {
			//instructions
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	
		quit.setOnAction(e -> {
			try {
				System.exit(0);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		menuOptions.getChildren().addAll(play, instruct, quit);
		border.setCenter(menuOptions);
		root.getChildren().addAll(background, border);
		
	}
	
	
	private void initialize() {
		menuOptions = new VBox(20);
		border = new BorderPane();
		play = new Button("Start");
		instruct = new Button("How to Play");
		quit = new Button("Quit");
	}
}

