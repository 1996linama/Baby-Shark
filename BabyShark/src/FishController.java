import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FishController {

	List<EnemyFish> enemies = new ArrayList<EnemyFish>();
	private Random random = new Random();
	int numOfEnemies;
	
	public FishController() {}
	
	public List<EnemyFish> getEnemies(){
		return enemies;
	}
	
	public void populateEnemies() {	
		while (enemies.size() < numOfEnemies) {
			addFish(createFish());
		}
	}
	
	private EnemyFish createFish() {
		int rand = random.nextInt(FishType.values().length);
		return new EnemyFish(FishType.values()[rand]);
	}
	
	public void setNumOfEnemies(int numOfEnemies) {
		this.numOfEnemies = numOfEnemies;
	}
	
	private void addFish(EnemyFish fish) {
		enemies.add(fish);
		Game.add(fish);
	}

	public void removeFish(Fish fish) {
		enemies.remove(fish);
	}

}
