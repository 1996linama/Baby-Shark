import javafx.stage.Stage;

public class Frame extends Stage {

	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	private final static String BS_TITLE = "Baby Shark";

	public final static int getMinX() {
		return -WIDTH / 2;
	}

	public final static int getMaxX() {
		return WIDTH / 2;
	}

	public final static int getMinY() {
		return -HEIGHT / 2;
	}

	public final static int getMaxY() {
		return HEIGHT / 2;
	}

	public Frame(Stage primaryStage) {
		primaryStage.setHeight(HEIGHT);
		primaryStage.setWidth(WIDTH);
		primaryStage.setTitle(BS_TITLE);
		primaryStage.setResizable(false);
	}

}
