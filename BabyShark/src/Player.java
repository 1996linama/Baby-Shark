import javafx.scene.image.*;

public class Player extends Fish {

	public void addScore(int num) {
		score = score + num;
		playerSprite = new Image(getClass().getResourceAsStream("/res/shark.png"), playerSprite.getWidth() + num/2,
				playerSprite.getHeight() + num/2, true, true);
		updateImage(playerSprite);
	}

	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/shark.png"), 80, 80, true, true);

	public void setSprite() {
		playerSprite = new Image(getClass().getResourceAsStream("/res/shark.png"), 50, 50, true, true);
		updateImage(playerSprite);
	}
	
	public Player() {
		super(3, "/res/shark.png");
		setSprite();

	}

	@Override
	public void run() {
	}

}
