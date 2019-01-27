import java.io.InputStream;
import java.util.ArrayList;

import javafx.scene.image.Image;

public final class Constant {

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
	
	private final static ArrayList<String> fishImagesPath = new ArrayList<>();
	
	public final static ArrayList<InputStream> PLAYER_IMAGES_PATH = new ArrayList<InputStream>();
	
	public final static ArrayList<Image> PLAYER_IMAGES = new ArrayList<>();
	
	public final void initializeImages() {
		for(int i = 0; i < fishImages.length; i++) {
			fishImagesPath.add(fishImages[i]);
		}
		
		for(int i = 0; i < playerImages.length; i++) {
			PLAYER_IMAGES.add(new Image(getClass().getResourceAsStream(playerImages[i])));
		}
		
		
	}

}
