import javafx.scene.image.*;

public class Player extends ImageView {
	
	int speed;
	double x;
	double y;
	int width;
	int height;
	boolean direction;
	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/fish1.png"));
	
	public void setP(double x, double y) {
		this.x = x;
		this.y = y;
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public void flipLeft() {
		this.setScaleX(-1);
	}
	
	public void flipRight() {
		this.setScaleX(1);
	}
	
	public double getPX() {
		return this.getX();
	}
	
	public double getPY() {
		return this.getY();
	}
	

	public void updateLocation() {
	//	playerSprite.relocate(x, y);
	}
	
	public Player() {
		//initialize the character
		setImage(playerSprite);

	}


}
