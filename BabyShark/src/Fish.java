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
	Random random = new Random();
	boolean isReversed;
	boolean isAlive;

	public Fish(double speed, int score, String string) {
		int reversed = random.nextInt(2);
		
		this.speed = speed;
		this.score = score;
		isAlive = true;
		updateImage(new Image(getClass().getResourceAsStream(string)));

		if(reversed == 1) {
			isReversed = false;
			flipLeft();
		} else {
			isReversed = true;
		}
		
	}

	public Fish(double size) {
	}

	public Fish(double speed, String picUrl) {
		this.speed = speed;
		updateImage(new Image(getClass().getResourceAsStream(picUrl)));
	}

	public void run() {
		random = new Random();
		y = random.nextInt(800) - 400;
		this.setTranslateY(y);
		if(isReversed) {
			x = -400 - getWidth() - random.nextInt(200); // out of the frame
		} else {
			x = 400 + getWidth() + random.nextInt(200);
		}
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(x, y);
				if(isReversed) {
					x += getSpeed();
					
					if (x > 480.0) {
						this.stop();
						kill();
					}
					
				} else {
					x -= getSpeed();
					
					if (x < -480.0) {
						this.stop();
						kill();
					}
				}
				
			}

		};

		timer.start();
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

	public void kill() {
		isAlive = false;
		setVisible(false);
	}

	public boolean isAlive() {
		return isAlive;
	}

}
