public class Levels {
	
	Integer levelNum = 0;
	int scoreRequirement = 0;
	int numOfEnemies = 0;
	double playerSpeed = 0;
	int sizeIncrease = 0;
	
	Levels(Integer levelNum, int score, int numOfEnemies, int sizeIncrease, double playerSpeed){
		this.levelNum = levelNum;
		this.scoreRequirement = score;
		this.numOfEnemies = numOfEnemies;
		this.playerSpeed = playerSpeed;
		this.sizeIncrease = sizeIncrease;
	}
	
	public int getScoreRequirement() {
		return scoreRequirement;
	}
	
	public double getSpeed() {
		return playerSpeed;
	}
	
	public int getNumOfEnemies() {
		return numOfEnemies;
	}
	
	public int getSizeIncrease() {
		return sizeIncrease;
	}
	
	public Integer getLevelNumber() {
		return levelNum;
	}
	
}