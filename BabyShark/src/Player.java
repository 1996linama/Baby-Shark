import javafx.scene.image.*;

public class Player extends Fish {
	
	int speed;
	double x;
	double y;
	int width;
	int height;
	boolean direction;
	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/fish1.png"));
	
	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
	public double getWidth() {
		return playerSprite.getWidth();
	}
	public double getHeight() {
		return playerSprite.getHeight();
	}
	
	public double getSize() {
		return playerSprite.getWidth() * playerSprite.getHeight();
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
	
	public void collision() {
		
	}
	
	public Player() {
		//initialize the character
		setImage(playerSprite);

	}


	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getFrequency() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void run() {
		// TODO Auto-generated method stub
		
	}


}
