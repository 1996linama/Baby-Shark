import java.io.InputStream;
import java.util.ArrayList;

import javafx.scene.image.Image;

public final class Constant {

	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;
	public final static String BS_TITLE = "Baby Shark";

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
	
	private final String[] bgImages = {
			"res/dimmedbg.png",
			"res/dimmegbg2.png",
			"res/menubg.png"
	};
	
	private final String[] playerImages = {
			"res/shark1.png",
			"res/shark2.png",
			"res/shark3.png",
			"res/shark4.png",
			"res/shark3.png",
			"res/shark2.png",
			"res/shark1.png",
	};
	
	private final String[] fishImages = {
			"res/fish0.png",
			"res/fish1.png",
			"res/fish2.png",
			"res/fish3.png",
			"res/fish4.png",
			"res/fish5.png",
			"res/fish6.png",
			"res/fish7.png",
	};
	
	private final static ArrayList<String> fishImagesPath = new ArrayList<String>();
	
	public final static ArrayList<InputStream> PLAYER_IMAGES_PATH = new ArrayList<InputStream>();
	
	public final void initializeImages() {
		for(int i = 0; i < fishImages.length; i++) {
			fishImagesPath.add(fishImages[i]);
		}
		
		for(int i = 0; i < playerImages.length; i++) {
			PLAYER_IMAGES_PATH.add(getClass().getResourceAsStream(playerImages[i]));
		}
	}

}
