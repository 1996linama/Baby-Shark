import javafx.stage.Stage;

public class Frame extends Stage {

	private final static int width = 800;
	private final static int height = 600;
	private final static String BS_TITLE = "Baby Shark";

	public static int getMinX() {
		return -width / 2;
	}

	public static int getMaxX() {
		return width / 2;
	}

	public static int getMinY() {
		return -height / 2;
	}

	public static int getMaxY() {
		return height / 2;
	}

	public Frame(Stage primaryStage) {
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		primaryStage.setTitle(BS_TITLE);
		primaryStage.setResizable(true); // should be false, temp resize true
		primaryStage.show();

	}

	public Stage getMenu() {
		return null;
	}

	public void setMenu() {

	}

}
