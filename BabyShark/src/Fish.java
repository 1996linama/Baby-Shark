import javafx.scene.image.ImageView;

public abstract class Fish extends ImageView {

	int speed;
	int frequency;
	int x;
	int y;
	ImageView fishSprite;
	
	public abstract int getSpeed();
	public abstract int getFrequency();
	public abstract void run();
	
	Fish() {}
	
}
