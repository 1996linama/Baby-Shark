import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public abstract class Fish extends ImageView {

	double speed;
	double frequency;
	double x;
	double y;
	double width;
	double height;
	int score;
	ImageView fishSprite;
	Image sprite;
	Random randomGenerator;
	boolean isAlive;

	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.setX(x);
		this.setY(y);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public double getSpeed() {

		return this.speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getFrequency() {
		return frequency;
	}
	
	public void flipLeft() {
		this.setScaleX(-1);
	}
	
	public void flipRight() {
		this.setScaleX(1);
	}

	public abstract void run();
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getSize() {
		return this.getHeight() * this.getWidth();
	}
	
	public void updateImage(Image sprite) {
		this.sprite = sprite;
		setImage(sprite);
		this.width = sprite.getWidth();
		this.height = sprite.getHeight(); 
	}
	
	Fish(double speed, int score) {
		this.speed = speed;
		this.score = score;
		isAlive = true;
	}

	public double getLocationX() {
		return this.x;
	}

	public double getLocationY() {
		return this.y;
	}
	
	protected void setLife() {
		isAlive = false;
		setVisible(false);
	}
	protected boolean isAlive() {
		return isAlive;
	}
	
}
