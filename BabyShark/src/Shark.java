import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shark extends Fish {

	private int speed;
	private int frequency;
	private int x;
	private int y;
	private Image fishSprite = new Image(getClass().getResourceAsStream("/res/fish0.png"));
	
	
	public int getSpeed() {
		return speed;
	}

	public int getFrequency() {
		return frequency;
	}

	public void run() {
		
	}
	
	Shark(){
		speed = 30;
		frequency = 5;
		setImage(fishSprite);
		
		//create the shark in a random location
		
	}
	
	

}
