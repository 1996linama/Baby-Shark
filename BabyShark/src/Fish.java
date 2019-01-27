import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Fish extends ImageView {

	double speed;
	double x = -1000;
	double y = -1000;
	double width;
	double height;
	Image sprite;
	AnimationTimer timer;
	
	// used for Enemies
	public Fish(FishType type) {
		setSpeed(type.getSpeed());
		updateImage(new Image(getClass().getResourceAsStream(type.getImage())));
	}

	// used for Player
	public Fish(double speed) {
		this.speed = speed;
	}

	public void kill() {
		setVisible(false);
	}
	
	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.setX(x);
		this.setY(y);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void flipLeft() {
		this.setScaleX(-1);
	}

	public void flipRight() {
		this.setScaleX(1);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
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

	public double getLocationX() {
		return x;
	}

	public double getLocationY() {
		return y;
	}

	public abstract void run();

}
