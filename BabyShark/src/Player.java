import javafx.scene.image.*;

public class Player extends Fish {
	
	int speed;
	double x;
	double y;
	double width;
	double height;
	boolean direction;
	boolean isAlive;
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
		//isAlive = false;
		
	}
	
	public void updateImage(Image sprite) {
		setImage(sprite);
		this.width = sprite.getWidth();
		this.height = sprite.getHeight(); 
	}
	
	public Player() {
		//initialize the character
		updateImage(playerSprite);
		isAlive = true;

	}


	public double getSpeed() {
		return 1;
	}


	public int getFrequency() {
		return 0;
	}


	public void run() {
	//handles Animation	
	}


	@Override
	protected double getLocationX() {
		return this.x;
	}


	@Override
	protected double getLocationY() {
		return this.y;
	}


	@Override
	public int getScore() {
		return 0;
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
