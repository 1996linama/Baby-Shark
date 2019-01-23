public enum FishType {
	PUFFERFISH(1.5, 10, "/res/fish3.png"),
	SHARK(1.5, 10, "/res/fish1.png"),
	CATFISH(1.2, 5, "/res/fish5.png"),
	TUNA(1.3, 15, "/res/fish4.png"),
	GUPPY(1.2, 3, "/res/fish0.png");
	//ORCA(1.1, 30, "res/orca1.png");
	
	FishType fType;
	
	private final double speed;
	private final int score;
	private final String image;
	
	private FishType(double speed, int score, String image) {
		this.speed = speed;
		this.score = score;
		this.image = image;
	}

	public double getSpeed() {
		return speed;
	}

	public int getScore() {
		return score;
	}

	public String getImage() {
		return image;
	}
	
	

}