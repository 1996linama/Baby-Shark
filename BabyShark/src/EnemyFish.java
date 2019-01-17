import java.util.Random;

public class EnemyFish extends Fish {
	
	private static Random random = new Random()
;
	
	public static Fish createFish() {
		int rand = random.nextInt(FishType.values().length);
		return new EnemyFish(FishType.values()[rand]);
	}
	
	public EnemyFish(FishType type) {
		super(type);

	}
	

	@Override
	public void flipLeft() {
		this.setScaleX(-1);
		this.speed = -this.speed;
		this.x = 400 + getWidth() + random.nextInt(200);
	}

	@Override
	public void flipRight() {
		this.setScaleX(1);
	}

}
