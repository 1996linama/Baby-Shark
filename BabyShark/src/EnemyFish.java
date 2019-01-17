
public class EnemyFish extends Fish {

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
