import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Catfish extends Fish {

	private Random random;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish5.png"));
	double i;
	
	public void run() {
		random = new Random();
		double y = random.nextInt(300) + -300;
		double x;
		this.setTranslateY(y);

		i = -400 - getWidth(); //out of the frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(i * getSpeed(), y);
				i++;
				if(i > 420.0) {
					this.stop();
					setLife();
				}
			}
			
		};
				
		timer.start();
	}
	
	Catfish(){
		super(1.2, 5);
		super.setImage(fishSprite);
		//this.setX(-400 - getWidth());
		//this.setY(y);
		run();
	}
	

}
