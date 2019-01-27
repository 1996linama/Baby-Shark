import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FishController {

	List<EnemyFish> enemies = new ArrayList<EnemyFish>();
	int numOfEnemies;
	
	public FishController() {}
	
	public void updateFish() {
		for (EnemyFish fish : new ArrayList<EnemyFish>(enemies)) {
			if (Game.getPlayer().getX() != 0 && fish.isColliding(Game.getPlayer())) {
				if (Game.getPlayer().canPlayerEatEnemy(fish)) {
					Game.setScore(fish.getFishValue());
					removeFish(fish);
				} else {
					removePlayer(Game.getPlayer());
				}
			}
			
			if(!fish.isVisible()) {
				removeFish(fish);
			}

		}
	}
	
	public void populateEnemies() {	
		while (enemies.size() < numOfEnemies) {
			addFish(EnemyFish.createFish());
		}
	}
	
	public void setNumOfEnemies(int numOfEnemies) {
		this.numOfEnemies = numOfEnemies;
	}
	
	private void addFish(EnemyFish fish) {
		enemies.add(fish);
		Game.add(fish);
	}
	
	private void removePlayer(Player player) {
		player.kill();
		Game.remove(player);
	}

	private void removeFish(Fish fish) {
		Game.remove(fish);
		enemies.remove(fish);
	}
	
	public void clearEnemies() {
		enemies.clear();
	}

}
