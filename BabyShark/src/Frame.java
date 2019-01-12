import javafx.stage.Stage;

public class Frame extends Stage{

	private final int width = 800;
	private final int height = 600;
	private final static String BS_TITLE = "Baby Shark";
	
	public Frame(Stage primaryStage) {
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		primaryStage.setTitle(BS_TITLE);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
}
