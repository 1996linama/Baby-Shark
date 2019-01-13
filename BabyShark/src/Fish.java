import javafx.scene.image.ImageView;
import java.util.Random;

public abstract class Fish extends ImageView {

	int speed;
	int frequency;
	double x;
	double y;
	int score;
	ImageView fishSprite;
	Random randomGenerator;

	public abstract void updateLocation(double x, double y);
	public abstract double getSpeed();
	public abstract double setSpeed();
	public abstract int getFrequency();
	public abstract int setFrequency();
	public abstract void flipLeft();
	public abstract void flipRight();
	public abstract void run();
	public abstract int getScore();
	public abstract double getWidth();
	protected abstract double getSize();
	
	Fish() {}
	
	protected abstract double getHeight();
	protected abstract double getLocationX();
	protected abstract double getLocationY();
	
}
