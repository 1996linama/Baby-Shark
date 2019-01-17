
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.*;

public class Player extends Fish {
	
	
	private String[] playerSprites = {"res/shark1.png","res/shark2.png","res/shark3.png", "res/shark4.png", "res/shark3.png", "res/shark2.png", "res/shark1.png", }; 
	private int tick = 0;
	private int sizeIncrease = 0;

	public Player() {
		super(4, "/res/shark.png");
		setSprite();
		run();
	}
	
	public void addScore(int fishScore) {
		score = score + fishScore;
		sizeIncrease = fishScore;
	}

	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/shark.png"), 60, 60, true, true);

	public void setSprite() {
		this.width = playerSprite.getWidth();
		this.height = playerSprite.getHeight();
		setImage(playerSprite);
	}
	
	// check if enemies can be eaten
	public boolean canPlayerEatEnemy(Fish fish) {
		return fish.getSize() <= this.getSize();
	}
	
	@Override
	public void updateImage(Image sprite) {
		setImage(sprite);
		this.width = this.width + sizeIncrease/2;
		this.height = this.height + sizeIncrease/2;
		this.sizeIncrease = 0; //resets the increase
	}

	@Override
	public void run() {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				playerSprite = new Image(getClass().getResourceAsStream(playerSprites[tick++]), width, height, true, true);
				updateImage(playerSprite);
				if (tick >= playerSprites.length) {
					tick = 0;
				    }
			}
		};

		timer.start();
		
		
	}

}
