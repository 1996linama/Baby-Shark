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

class LevelPair implements Comparator<LevelPair> {
	Integer lNum;
	Levels level;
	
	LevelPair(int lNum, Levels level){
		this.lNum = lNum;
		this.level = level;
	}
	
	public Levels getLevel() {
		return this.level;
	}

	public int compareTo(Object o) {
		LevelPair other = (LevelPair) o;
		if(Integer.valueOf(other.lNum) > Integer.valueOf(lNum)) {
			return -1;
		}
		return 1;
	}

	@Override
	public int compare(LevelPair arg0, LevelPair arg1) {
		if(Integer.valueOf(arg1.lNum) > Integer.valueOf(arg0.lNum)) {
			return -1;
		}
		return 1;
	}
	
	
}

public class LevelGenerator {

	private Random random = new Random();
	
	
	public LevelGenerator() {
		initLevels();
		Game.setCurrentLevels(levelMap.get(Integer.valueOf(0)));
	}
	
	
	//variables initialized to RANDOM numbers for now
	private int scoreRequirement = 0;
	private int numOfEnemies = 12;
	private double speed = 3.0;
	private int playerSizeIncrease = 1;
	private int minSizeIncrease = 1;
	private int maxSizeIncrease = 2;	
	private int minScoreRequirement = 20;
	private int maxScoreRequirement = 30;
	private int minNumOfEnemies = 5;
	private int maxNumOfEnemies = 5;
	

	static HashMap<Integer, Levels> levelMap= new HashMap<Integer, Levels>();
	static ArrayList<Levels> unvisited = new ArrayList<Levels>();
	static PriorityQueue<LevelPair> pq = new PriorityQueue<LevelPair>(4, 
			new Comparator<LevelPair>() {
				public int compare(LevelPair one, LevelPair two) {
					return one.compareTo(two);
		}
	});
	
	
	private void initLevels() {
		for(int i = 0; i < 20; i++) {
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
	//will be called every time there is a score change?
	public static void changeLevels() {
		
		//checks if layer zero
		/*
		if(Game.getCurrentLevels() == levelMap.get(Integer.valueOf(0))) {
			pq.add(new LevelPair(Integer.valueOf(0), Game.getCurrentLevels()));
		} else {
			pq.add(new LevelPair(Game.getCurrentLevels().getLevelNumber(), Game.getCurrentLevels()));
		}
		
		while(!pq.isEmpty()) {
			LevelPair curr = pq.poll(); //will be null first
		
			//iterate through the map in order to retrieve levels (maybe an arraylist<Levels> would be easier)
			for (Entry<Integer, Levels> item : levelMap.entrySet()) {
				if(Game.getScore() >= item.getValue().getScoreRequirement() && !item.getValue().getCompleted()) {
					//curr = new LevelPair(item.getKey(), item.getValue());
					item.getValue().setCompletion();
					next = item.getValue();
					//pq.add(curr);
				}
				
			}
	
			Game.setCurrentLevels(next);
		*/	
			Levels next = null;
			for(Levels level : unvisited) {
				if(Game.getScore() >= level.getScoreRequirement()){
					Game.setCurrentLevels(level);
					unvisited.remove(level);
				}
			}
			
				
		}
//	}
		
	
	private int getScoreReq() {
		minScoreRequirement = scoreRequirement;
		return scoreRequirement;
		
	}
	
	private void setScoreReq() {
		scoreRequirement += random.nextInt(maxScoreRequirement);
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
	
	private void setNumOfEnemies() {}
	private int getNumOfEnemies() {
		return numOfEnemies;
	}
	
}

