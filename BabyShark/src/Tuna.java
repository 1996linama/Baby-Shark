
import java.util.Random;


import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Tuna extends Fish {

	private Random random;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish4.png"));
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
					kill();
				}
			}
			
		};
				
		timer.start();
	}

	Tuna(){
		super(1.3, 15);
		super.updateImage(fishSprite);
		//this.setX(-400 - getWidth());
		//this.setY(y);
		run();
	}

}
