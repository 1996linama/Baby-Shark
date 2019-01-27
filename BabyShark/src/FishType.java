import javafx.scene.image.*;

public enum FishType {
	PUFFERFISH(1.5, 15, getImage("/res/fish3.png", 65, 59)),
	COD(1.5, 10, getImage("/res/fish1.png", 60, 45)),
	CATFISH(1.2, 5, getImage("/res/fish5.png", 57, 32)),
	TUNA(1.3, 15, getImage("/res/fish4.png", 58, 46)),
	GUPPY(1.2, 3, getImage("/res/fish0.png", 44, 26)),
	BLUETANG(1.9, 7, getImage("/res/fish6.png", 60, 40)),
	ORCA(1.6, 35, getImage("/res/orca1.png", 150, 121));

	private final double speed;
	private final int value;
	private final Image image;

	private FishType(double speed, int value, Image image) {
		this.speed = speed;
		this.value = value;
		this.image = image;
	}
	
	public double getSpeed() {
		return speed;
	}

	public int getValue() {
		return value;
	}
	
	public Image getImage() {
		return image;
	}

	private static Image getImage(String url, int width, int height) {
		return new Image(url, width, height, true, true);
	}
	

}