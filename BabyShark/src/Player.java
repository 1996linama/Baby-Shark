
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.*;

public class Player extends Fish {
	
	//TODO: will redefine playerSprites in the constant class
	private String[] playerSprites = {"res/shark1.png","res/shark2.png","res/shark3.png", "res/shark4.png", "res/shark3.png", "res/shark2.png", "res/shark1.png", }; 
	private int frame = 0;
	private int sizeIncrease = 0;
	private Levels playerLevel = null;
	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/shark.png"), 60, 40, true, true);
	
	public Player() {
		super(4, "/res/shark.png");
		setSprite();
		run();
	}
	
	public void addScore(int fishScore) {
		score = score + fishScore;
	}
	
	private boolean isLeveled() {
		if(playerLevel != Game.getCurrentLevels()) {
			playerLevel = Game.getCurrentLevels();
			return true;
		}
		
		return false;
	}

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
		if(isLeveled()) {
			this.sizeIncrease = Game.getCurrentLevels().getSizeIncrease();
			//setSizeIncrease(Game.getCurrentLevels().getSizeIncrease());
		}
		this.width = this.width + sizeIncrease;
		this.height = this.height + sizeIncrease;
		this.sizeIncrease = 0;
	}
	
	@Override
	public void run() {
		//handles the animation of the sprite
	
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				playerSprite = new Image(getClass().getResourceAsStream(playerSprites[frame++]), width, height, true, true);
				updateImage(playerSprite);
				setSpeed(playerLevel.getSpeed());
				if (frame >= playerSprites.length) {
					frame = 0;
				    }
			}
		};

		timer.start();
	}

}
