import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Shark extends Fish {

	private int speed;
	private int frequency;
	private double x;
	private double y;
	private int time = -100;
	int xmove;
	private double width;
	private boolean reverse;
	private boolean isAlive;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish6.png"));
	
	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.setTranslateX(x);
		this.setTranslateY(y);
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
	
	public void move() {

		while(time < 100000) {
			
			this.setTranslateX(xmove++);
			time++;
		}
		System.out.println("Done!");
	}
	
/*	
	public void remove() {
		if(this.getX() > )
	}
*/
	
	public int getFrequency() {
		return frequency;
	}

	public void run() {
		Timeline timeline = new Timeline();
		KeyValue kv;
	//	this.setTranslateY(value);

		if(reverse) {
			this.setTranslateX(400 + getWidth());
			flipLeft();
			kv = new KeyValue(this.translateXProperty(), -400 - getWidth());
		} else {
			this.setTranslateX(-400 - getWidth());
			kv = new KeyValue(this.translateXProperty(), 400 + getWidth());	
		}
		
		timeline.setAutoReverse(false);
		KeyFrame kf = new KeyFrame(Duration.millis(9000), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();		
	}
	
	Shark(){
		isAlive = true;
		setImage(fishSprite);
		run();
	}





	@Override
	public double getWidth() {
		return fishSprite.getWidth();
	}
	
	

}
