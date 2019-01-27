import java.util.Random;

import javafx.animation.AnimationTimer;

public class EnemyFish extends Fish {
	
	private static Random random = new Random();
	boolean isReversed;	
	int fishValue;

	public EnemyFish(FishType type) {
		super(type);
		isReversed = random.nextBoolean();
		fishValue = type.getValue();
		x = -400 - getWidth() - random.nextInt(200); // out of the frame
		y = random.nextInt(800) - 400;
		if(isReversed) {
			flipLeft();
		}
		updateLocation(x, y);
		run();
	}
	
	public int getFishValue() {
		return fishValue;
	}

	public void setFishValue(int fishValue) {
		this.fishValue = fishValue;
	}
	
	public boolean isColliding(Fish other) {
		return this.getBoundsInParent().intersects(other.getBoundsInParent());
	}
	
	public boolean isOffscreen(double x) {
		return (!isReversed && x > 480.0) || (isReversed && x < -480.0);
	}

	@Override
	public void flipLeft() {
		this.setScaleX(-1);
		speed = -speed;
		x = 400 + getWidth() + random.nextInt(200);
	}

	@Override
	public void run() {	
		timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(x, y);
				x += getSpeed();
				if(isOffscreen(x)) {
					setVisible(false);
				}
			}
		};
		timer.start();
	}
	
	
}
