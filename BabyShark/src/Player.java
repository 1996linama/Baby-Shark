import javafx.scene.image.*;

public class Player extends Fish {
	
	public void addScore(int num) {
		score = score + num;
	}
	
	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/fish1.png"));
	
	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.setX(x);
		this.setY(y);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public Player() {
		//initialize the character
		super(4, 0);
		super.updateImage(playerSprite);

	}

	public void run() {
	//handles Animation	
	}


}
