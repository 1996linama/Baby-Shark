import javafx.scene.image.*;

public class Player extends ImageView {
	
	int speed;
	int x;
	int y;
	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/fish1.png"));
	private Controller controller;
	
	//boolean moveUp, moveDown, moveLeft, moveRight;
	
	public double getPX() {
		return this.getX();
	}
	
	public double getPY() {
		return this.getY();
	}
	
	public Player(Controller controller) {
		//initialize the character
		setImage(playerSprite);
		this.controller = controller;
		System.out.println("Created");
	}


}
