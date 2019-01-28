import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;

public class LevelGenerator {

	private Random random = new Random();
	private int numberOfLevels = 20;
	private int scoreRequirement = 0;
	private int numOfEnemies = 12;
	private double speed = 3.0;
	private int playerSizeIncrease = 1;
	private int minSizeIncrease = 1;
	private int maxSizeIncrease = 3;	
	private int minScoreRequirement = 10;
	private int maxScoreRequirement = 15;
	private int minNumOfEnemies = 10;
	private int maxNumOfEnemies = 15;

	public LevelGenerator() {
		initLevels();
		Game.setCurrentLevel(levelMap.get(Integer.valueOf(0)));
	}
		
	HashMap<Integer, Level> levelMap= new HashMap<Integer, Level>();
	static ArrayList<Level> unvisited = new ArrayList<Level>();
	
	private void initLevels() {
		for(int i = 0; i < numberOfLevels; i++) {
			Level next = generateLevel(i);
			levelMap.put(new Integer(i), next);
			unvisited.add(next);
		}
	}
	
	private Level generateLevel(int levelnum) {
		//randomize score that is required to go onto the next level
		setScoreRequirement();
		
		//randomize player benefits received after "leveling up"
		setSizeIncrease();
		setSpeed();
		
		//randomize but increase danger to player as levels go up
		setNumOfEnemies();
		
		return new Level(new Integer(levelnum), getScoreRequirement(), 
				getNumOfEnemies(), getSizeIncrease(), getSpeed());
	}
	
	//this changes the level based on the score
	public static void changeLevel() {	
		for(Level level : new ArrayList<>(unvisited)) {
			if(Game.getScore() >= level.getScoreRequirement()){
				Game.setCurrentLevel(level);
				unvisited.remove(level); 
				return;
			}
		}
	}
	
	private int getScoreRequirement() {
		return scoreRequirement;
	}
	
	private void setScoreRequirement() {
		scoreRequirement = randomize(maxScoreRequirement, minScoreRequirement);
		minScoreRequirement = scoreRequirement;
		maxScoreRequirement += 20;
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
		minNumOfEnemies = numOfEnemies;
		maxNumOfEnemies += randomize(3, 0);
		numOfEnemies = randomize(maxNumOfEnemies, minNumOfEnemies);
	}
	
	private int getNumOfEnemies() {
		return numOfEnemies;
	}
	
	private int randomize(int max, int min) {
		return random.nextInt(max - min) + min;
	}
	
}

