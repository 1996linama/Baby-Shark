import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

public class MainMenu extends Scene {
	
	private VBox menuOptions;
	private BorderPane border;

	private Button play, instruct, quit;

	AudioClip clickEffect = new AudioClip(
			getClass().getResource("/res/button.mp3").toString());
	
	public MainMenu(StackPane root) {
		super(root);
		this.getStylesheets().addAll(
				this.getClass().getResource("style.css").toExternalForm());
		root.setId("menu");
		initialize();

		play.setOnAction(e -> {
			try {
				clickEffect.play();
				BabyShark.setGame();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		instruct.setOnAction(e -> {
			try {
			clickEffect.play();
			BabyShark.setScene(new Instructions(new StackPane()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	
		quit.setOnAction(e -> {
			try {
				clickEffect.play();
				System.exit(0);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		menuOptions.getChildren().addAll(play, instruct, quit);
		border.setCenter(menuOptions);
		root.getChildren().addAll(border);
		
	}
	
	
	private void initialize() {
		menuOptions = new VBox(20);
		border = new BorderPane();
		play = new Button("Start");
		instruct = new Button("How to Play");
		quit = new Button("Quit");
	}
	
	private class Instructions extends Scene {
		private VBox options;
		Button backToMainMenu;
		
		public Instructions(StackPane root) {
			super(root);
			this.getStylesheets().addAll(
					this.getClass().getResource("style.css").toExternalForm());
			options = new VBox(20);
			backToMainMenu = new Button("Back");
			
			backToMainMenu.setOnAction(e -> {
				try {
					clickEffect.play();
					BabyShark.setMenu();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});			
			options.getChildren().add(backToMainMenu);
			root.getChildren().add(options);
		}
		
	}
}

