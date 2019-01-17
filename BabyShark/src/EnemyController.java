import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class EnemyController {

	List<Fish> enemies = new ArrayList<Fish>();
	
	public EnemyController() {
	}
	
	public void updateFish() {
		for (Fish fish : new ArrayList<Fish>(enemies)) {
			if (Game.getPlayer().getX() != 0 && fish.isColliding(Game.getPlayer())) {
				if (Game.getPlayer().canPlayerEatEnemy(fish)) {
					Game.player.addScore(fish.getScore());
					removeFish(fish);
				} else {
					removeFish(Game.getPlayer());
				}

			}

			checkFishBounds(fish); // checks Fish off screen.
		}
	}
	
	public void populateEnemies() {	
		while (enemies.size() < 8) {
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
		Game.addToScreen(fish);
	}

	private void removeFish(Fish fish) {
		fish.kill();
		Game.removeFromScreen(fish);
		enemies.remove(fish);
	}

}
