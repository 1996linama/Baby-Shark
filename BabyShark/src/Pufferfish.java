import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Pufferfish extends Fish {
	
	private Random random;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish3.png"));
	double i;
	
	public void run() {
		random = new Random();
		double y = random.nextInt(300) + -300;
		this.setTranslateY(y);
		i = -400 - getWidth(); //out of the frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				updateLocation(i * getSpeed(), y);
				i++;
				if(i > 420.0) {
					kill();
					this.stop();
				}
			}
			
		};
		
		timer.start();
	}

	Pufferfish(){
		super(0.8, 0);
		super.updateImage(fishSprite);
		//updateLocation(x, y);
		run();
	}


	@Override
	public double getSize() {
		return Double.MAX_VALUE;
	}
	

}
