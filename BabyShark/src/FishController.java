import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FishController {

	List<Fish> enemies = new ArrayList<Fish>();
	
	public FishController() {}
	
	public void updateFish() {
		for (Fish fish : new ArrayList<Fish>(enemies)) {
			if (Game.getPlayer().getX() != 0 && fish.isColliding(Game.getPlayer())) {
				if (Game.getPlayer().canPlayerEatEnemy(fish)) {
					Game.getPlayer().addScore(fish.getScore());
					removeFish(fish);
				} else {
					removeFish(Game.getPlayer());
				}
			}
			checkFishBounds(fish);
		}
	}
	
	public void populateEnemies() {	
		while (enemies.size() < Game.getCurrentLevels().getNumOfEnemies()) {
			addFish(EnemyFish.createFish());
		}
	}
	
	private void checkFishBounds(Fish fish) {
		if (fish.isOffscreen(fish.getLocationX())) {
			removeFish(fish);
		}
	}
	
	private void addFish(Fish fish) {
		enemies.add(fish);
		Game.add(fish);
	}

	private void removeFish(Fish fish) {
		fish.kill();
		Game.remove(fish);
		enemies.remove(fish);
	}
	
	public void clearEnemies() {
		enemies.clear();
	}

}
