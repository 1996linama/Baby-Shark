import javafx.animation.AnimationTimer;
import javafx.scene.image.*;

public class Player extends Fish {
	
	private String[] playerSprites = {"res/shark1.png","res/shark2.png","res/shark3.png", "res/shark4.png", "res/shark3.png", "res/shark2.png", "res/shark1.png"}; 
	private int frame = 0;
	private Level playerLevel = null;
	private Image playerSprite = new Image(getClass().getResourceAsStream("/res/shark.png"), 60, 40, true, true);
	
	public Player() {
		super(4);
		updateImage(playerSprite);
		width = playerSprite.getWidth();
		height = playerSprite.getHeight();
		run();
	}
	
	private boolean isLeveled() {
		if(playerLevel != Game.getCurrentLevel()) {
			playerLevel = Game.getCurrentLevel();
			return true;
		}
		return false;
	}
	
	private int getSizeIncrease() {
		return (isLeveled())? Game.getCurrentLevel().getSizeIncrease():0;
	}
	
	public double getLocationX() {
		return x;
	}

	public double getLocationY() {
		return y;
	}
	
	public boolean canPlayerEatEnemy(Fish fish) {
		return fish.getSize() <= this.getSize();
	}
	
	@Override
	protected void updateImage(Image sprite) {
		setImage(sprite);
		int increase = getSizeIncrease();
		width += increase;
		height += increase;
	}
	
	@Override
	protected void run() {
		timer = new AnimationTimer() {
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
