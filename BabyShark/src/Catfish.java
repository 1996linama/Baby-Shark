import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Catfish extends Fish {

	private double speed;
	private int frequency;
	private double x;
	private double y;
	private boolean isAlive;
	private int score;
	private Random random;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish5.png"));
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
		random = new Random();
		double y = random.nextInt((300) + (int) getHeight());
		double x;
		this.setTranslateY(y);
		i = -400 - getWidth(); //out of the frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(i * speed, y);
				i++;
				if(i > 400.0) {
					this.stop();
				}
			}
			
		};
				
		timer.start();
	}
	
	protected double getHeight() {
		return fishSprite.getHeight();
	}

	Catfish(double x, double y){
		speed = 1.2;
		isAlive = true;
		this.score = 5;
		setImage(fishSprite);
		this.setX(-400 - getWidth());
		this.setY(y);
		run();
	}

	public double getWidth() {
		return fishSprite.getWidth();
	}

	@Override
	protected double getSize() {
		return getWidth() * getHeight();
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
