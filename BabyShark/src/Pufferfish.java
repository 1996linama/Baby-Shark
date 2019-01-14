import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Pufferfish extends Fish {
	
	private FishType type;
	private double speed;
	private int frequency;
	private double x;
	private double y;
	private boolean isAlive;
	private int score;
	private Random random;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish3.png"));
	double i;

	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.setX(x);
		this.setY(y);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public int getScore() {
		return this.score;
	}
	
	public double getLocationX() {
		return this.x;	
	}
	
	public double getLocationY() {
		return this.y;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setLife() {
		isAlive = true;
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
		//double y = random.nextInt(500) + getHeight();
		double x;
		this.setTranslateY(y);
		i = -400 - getWidth(); //out of the frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(i * speed, y);
				i++;
				if(i > 410.0) {
					//this.stop(); //if i want the fish to disappear
				}
			}
			
		};
		
		timer.start();
	}
	
	protected double getHeight() {
		return fishSprite.getHeight();
	}

	Pufferfish(double x, double y){
		this.type = FishType.PUFFERFISH;
		speed = 0.8;
		isAlive = true;
		setImage(fishSprite);
		updateLocation(x, y);
		run();
	}

	public double getWidth() {
		return fishSprite.getWidth();
	}

	@Override
	//Pufferfish is always dangerous to the player
	protected double getSize() {
		return Double.MAX_VALUE;
	}

	@Override
	public double setSpeed() {
		return 0;
	}

	@Override
	public int setFrequency() {
		return 0;
	}
	
	

}
