import javafx.scene.image.ImageView;
import java.util.Random;

public abstract class Fish extends ImageView {

	int speed;
	int frequency;
	double x;
	double y;
	boolean reverse;
	ImageView fishSprite;
	Random randomGenerator;

	public abstract void updateLocation(double x, double y);
	public abstract int getSpeed();
	public abstract int getFrequency();
	public abstract void flipLeft();
	public abstract void flipRight();
	public abstract void run();
	public abstract double getWidth();
	protected abstract double getSize();
	
	
	Fish() {}
	protected abstract double getHeight();
	protected abstract double getLocationX();
	protected abstract double getLocationY();
	
}
