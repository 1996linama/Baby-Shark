public enum Level {
	LEVEL_ZERO(0, 12, 3.0, 0),
	LEVEL_ONE(20, 13, 3.2, 4),
	LEVEL_TWO(40, 14, 3.3, 4),
	LEVEL_THREE(80, 14, 3.4, 4),
	LEVEL_FOUR(120, 15, 3.4, 4),
	LEVEL_FIVE(160, 15, 3.4, 4),
	LEVEL_SIX(250, 16, 3.5, 1),
	LEVEL_SEVEN(350, 16, 3.6, 1);

	
	private final double playerSpeed;
	private final int minScore;
	private final int sizeIncrease;
	private final int numOfEnemies;
	
	private Level (int minScore, int numOfEnemies, double playerSpeed, int sizeIncrease) {
		this.minScore = minScore;
		this.numOfEnemies = numOfEnemies;
		this.playerSpeed = playerSpeed;
		this.sizeIncrease = sizeIncrease;
		
	}

	public static Level getLevel(int score) {
		Level current = Level.LEVEL_ZERO;
		for(Level level: Level.values()) {
			if(score >= level.getMinScore()) {
				current = level;
			}
		}
		return current;
	}
	
	public int getMinScore() {
		return minScore;
	}
	
	public double getPlayerSpeed() {
		return playerSpeed;
	}
	
	public int getSizeIncrease() {
		return sizeIncrease;
	}
	
	public int getNumOfEnemies() {
		return numOfEnemies;
	}

	

}