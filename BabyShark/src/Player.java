import javafx.scene.image.*;

public class Player extends ImageView {
	
	int speed;
	double x;
	double y;
	int width;
	int height;
	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/fish1.png"));
	private ImageView player = new ImageView();
	private Controller controller;
	
	
	
	public double getPX() {
		return this.getX();
	}
	
	public double getPY() {
		return this.getY();
	}
	

	public void updateLocation() {
	//	playerSprite.relocate(x, y);
	}
	
	public Player(Controller controller) {
		//initialize the character
		setImage(playerSprite);
		this.controller = controller;
	}


}
