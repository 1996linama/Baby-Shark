public class Pufferfish extends Fish {
	
	public Pufferfish(){
		super(1.5, 10, "/res/fish3.png");
		run();
	}
	/* Pufferfish is always dangerous to the player */
	@Override
	public double getSize() {
		return Double.MAX_VALUE;
	}

}
