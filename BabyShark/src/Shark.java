import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Shark extends Fish {

	private int speed;
	private int frequency;
	private double x;
	private double y;
	private double width;
	private boolean reverse;
	private boolean isAlive;
	private Random random;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish6.png"));
	double i;
	
	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public double getLocationX() {
		return this.x;	
	}
	
	public double getLocationY() {
		return this.y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setLife() {
		isAlive = false;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void flipLeft() {
		this.setScaleX(-1);
	}
	
	public void flipRight() {
		this.setScaleX(1);
	}
	
	public int getFrequency() {
		return frequency;
	}

	public void run() {
		/*Timeline timeline = new Timeline();
		KeyValue kv;*/
		random = new Random();
		double num = random.nextInt((int) (600 - getHeight()));
		double x;
		this.setTranslateY(num);
		i = -400 - getWidth();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(i, num);
				i++;
				if(i > 400.0) {
					this.stop();
				}
			}
			
		};
		
		/*
		if(reverse) {
			x = 400 + getWidth();
			updateLocation(x, num);
			this.setTranslateX(x);
			flipLeft();
			kv = new KeyValue(this.translateXProperty(), -400 - getWidth());
		} else {
			x = -400 - getWidth();
			updateLocation(x, num);
			this.setTranslateX(x);
			kv = new KeyValue(this.translateXProperty(), 400 + getWidth());	
		}
		
		timeline.setAutoReverse(false);
		KeyFrame kf = new KeyFrame(Duration.millis(speed), kv);
		//timeline.getKeyFrames().add(kf); // animation starts of translation
		*/
		//timeline.play();
		
		timer.start();
	}
	
	protected double getHeight() {
		return fishSprite.getHeight();
	}

	Shark(){
		speed = 9000;
		isAlive = true;
		setImage(fishSprite);
		run();
	}

	public double getWidth() {
		return fishSprite.getWidth();
	}

	@Override
	protected double getSize() {
		return getWidth() * getHeight();
	}
	
	

}
