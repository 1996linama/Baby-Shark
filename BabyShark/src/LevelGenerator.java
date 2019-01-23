import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;


public class LevelGenerator {

	private Random random = new Random();
	//variables initialized to RANDOM numbers for now
	private int numberOfLevels = 20;
	private int scoreRequirement = 0;
	private int numOfEnemies = 10;
	private double speed = 3.0;
	private int playerSizeIncrease = 1;
	//TODO: is there a better way to decrease number of variables for maxes and mins
	private int minSizeIncrease = 1;
	private int maxSizeIncrease = 3;	
	private int minScoreRequirement = 20;
	private int maxScoreRequirement = 50;
	private int minNumOfEnemies = 10;
	private int maxNumOfEnemies = 15;

	public LevelGenerator() {
		initLevels();
		Game.setCurrentLevels(levelMap.get(Integer.valueOf(0)));
	}
		
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
		setScoreRequirement();
		
		//randomize player benefits received after "leveling up"
		setSizeIncrease();
		setSpeed();
		
		//randomize but increase danger to player as levels go up
		setNumOfEnemies();
		
		return new Levels(new Integer(levelnum), getScoreRequirement(), getNumOfEnemies(), getSizeIncrease(), getSpeed());
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
	
	private int getScoreRequirement() {
		minScoreRequirement = scoreRequirement;
		maxScoreRequirement += 50;
		return scoreRequirement;
	}
	
	private void setScoreRequirement() {
		scoreRequirement = randomize(maxScoreRequirement, minScoreRequirement);
	}
	
	private void setSizeIncrease() {
		playerSizeIncrease = randomize(maxSizeIncrease, minSizeIncrease);
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
		numOfEnemies = randomize(maxNumOfEnemies, minNumOfEnemies);
	}
	
	
	private int getNumOfEnemies() {
		minNumOfEnemies = numOfEnemies;
		maxNumOfEnemies += randomize(2, 0);
		return numOfEnemies;
	}
	
	private int randomize(int max, int min) {
		return random.nextInt(max - min) + min;
	}
	
}

