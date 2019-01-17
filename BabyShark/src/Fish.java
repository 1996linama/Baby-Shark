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
	int score;
	ImageView fishSprite;
	Image sprite;
	AnimationTimer timer;
	Random random = new Random();
	boolean isReversed;
	
	public Fish(FishType type) {
		
		isReversed = random.nextBoolean();
		
		this.speed = type.getSpeed();
		this.score = type.getScore();
		updateImage(new Image(getClass().getResourceAsStream(type.getImage())));
		x = -400 - getWidth() - random.nextInt(200); // out of the frame
		y = random.nextInt(800) - 400;
		
		if(isReversed) {
			flipLeft();
		}
		
		updateLocation(x, y);
		run();
		
	}

	public Fish(double speed, String picUrl) {
		this.speed = speed;
		updateImage(new Image(getClass().getResourceAsStream(picUrl)));
	}

	public void run() {	

		timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(x, y);
				x += getSpeed();
				if(isOffscreen(x)) {
					timer.stop();
					kill();
				}
			}
		};
		
		timer.start();
	}
	
	public boolean isOffscreen(double x) {
		return (!isReversed && x > 480.0) || (isReversed && x < -480.0);
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
		return this.speed;
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

	public double getLocationX() {
		return this.x;
	}

	public double getLocationY() {
		return this.y;
	}

}
