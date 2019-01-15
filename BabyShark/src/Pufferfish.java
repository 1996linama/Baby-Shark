import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Pufferfish extends Fish {
	
	private Random random;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish3.png"));
	double i;
	
	public void run() {
		random = new Random();
		double y = random.nextInt(400) + -400;
		double x;
		this.setTranslateY(y);
		i = -400 - getWidth(); //out of the frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(i * getSpeed(), y);
				i++;
				if(i > 410.0) {
					setLife();
					this.stop();
				}
			}
			
		};
		
		timer.start();
	}
	
	@Override
	public double getSize() {
		return 10000; //death by pufferfish always
	}

	Pufferfish(){
		super(0.8, 0);
		super.setImage(fishSprite);
		//updateLocation(x, y);
		run();
	}


/*	@Override
	public double getSize() {
		return Double.MAX_VALUE;
	}
*/
	

}
