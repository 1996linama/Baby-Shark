import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;

class Levels {
	
	//minimum score req
	Integer levelNum = 0;
	int scoreReq = 0;
	int numOfEnemies = 0;
	double playerSpeed = 0;
	int sizeIncrease = 0;
	int minScoreRange = 0;
	int maxScoreRange = 0;
	boolean isCompleted = false;
	
	Levels(Integer levelNum, int score, int num, int sizeIncrease, double speed){
		this.levelNum = levelNum;
		this.scoreReq = score;
		this.numOfEnemies = num;
		this.playerSpeed = speed;
		this.sizeIncrease = sizeIncrease;
	}
	
	public int getScoreRequirement() {
		return this.scoreReq;
		
	}
	
	public Integer getLevelNumber() {
		return levelNum;
	}
	
	public boolean getCompleted() {
		return isCompleted;
	}
	
	public void setCompletion() {
		isCompleted = true;
	}
	
}

public class LevelGenerator {

	private Random random = new Random();
	
	public LevelGenerator() {
		initLevels();
		Game.setCurrentLevels(levelMap.get(Integer.valueOf(0)));
	}
	
	//variables initialized to RANDOM numbers for now
	private int numberOfLevels = 20;
	private int scoreRequirement = 0;
	private int numOfEnemies = 12;
	private double speed = 3.0;
	private int playerSizeIncrease = 1;
	private int minSizeIncrease = 1;
	private int maxSizeIncrease = 5;	
	private int minScoreRequirement = 20;
	private int maxScoreRequirement = 50;
	private int minNumOfEnemies = 10;
	private int maxNumOfEnemies = 15;
	

	static HashMap<Integer, Levels> levelMap= new HashMap<Integer, Levels>();
	static ArrayList<Levels> unvisited = new ArrayList<Levels>();
	
	private void initLevels() {
		for(int i = 0; i < numberOfLevels; i++) {
			Levels next = generateLevel(i);
			levelMap.put(new Integer(i), next);
			unvisited.add(next);
		}
	}
	
	private Levels generateLevel(int levelnum) {
		
		//randomize score that is required to go onto the next level
		setScoreReq();
		
		//randomize player benefits received after "leveling up"
		setSizeIncrease();
		setSpeed();
		
		//randomize but increase danger to player as levels go up
		setNumOfEnemies();
		
		return new Levels(new Integer(levelnum), getScoreReq(), getNumOfEnemies(), getSizeIncrease(), getSpeed());
		
	}
	
	//this changes the level based on the score
	public static void changeLevels(int score) {	
		for(Levels level : new ArrayList<>(unvisited)) {
			if(score >= level.getScoreRequirement()){
				Game.setCurrentLevels(level);
				unvisited.remove(level);
			}
		}
	}
		
	
	private int getScoreReq() {
		minScoreRequirement = scoreRequirement;
		maxScoreRequirement += 50;
		return scoreRequirement;
	}
	
	private void setScoreReq() {
		scoreRequirement += random.nextInt(maxScoreRequirement) + minScoreRequirement;
	}
	
	private void setSizeIncrease() {
		playerSizeIncrease += random.nextInt(maxSizeIncrease) + minSizeIncrease;
	}

	private int getSizeIncrease() {
		return playerSizeIncrease;
	}

	private void setSpeed() {
		speed += 0.1;
	}
	
	private double getSpeed() {
		return speed;
	}
	
	private void setNumOfEnemies() {
		numOfEnemies = random.nextInt(maxNumOfEnemies) + minNumOfEnemies;
	}
	
	
	private int getNumOfEnemies() {
		minNumOfEnemies = numOfEnemies;
		maxNumOfEnemies += 2;
		return numOfEnemies;
	}
	
}

