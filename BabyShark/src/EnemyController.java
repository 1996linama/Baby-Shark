import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class EnemyController {
	
	
	private Random random = new Random();
	
	
	List<Fish> enemies = new ArrayList<Fish>();
	List<FishType> types = Arrays.asList(FishType.values());
	
	
	public EnemyController() {

		
	}
	
	public void updateFish() {
		for (Fish fish : new ArrayList<Fish>(enemies)) {
			if (Game.getPlayer().getX() != 0 && isColliding(fish, Game.getPlayer())) {
				if (canPlayerEatEnemy(fish)) {
					Game.player.addScore(fish.getScore());
					removeFish(fish);
				} else {
					removeFish(Game.getPlayer());
					//Game.gameOver("In Russia, fish eats YOU!");
				}

			}

			// when fish is off screen
			checkFishBounds(fish);
		}
	}

	
	private boolean isColliding(Fish fishOne, Fish fishTwo) {
		return fishOne.getBoundsInParent().intersects(fishTwo.getBoundsInParent());
	}
	
	private boolean canPlayerEatEnemy(Fish fish) {
		return fish.getSize() <= Game.getPlayer().getSize();
	}
	
	public void populateEnemies() {	
		while (enemies.size() < 8) {
			addFish(createFish());
		}
	}
	
	private void checkFishBounds(Fish fish) {
		if (fish.isOffscreen(fish.getLocationX())) {
			removeFish(fish);
		}
	}
	
	private Fish createFish() {
		int rand = random.nextInt(types.size());
		return new EnemyFish(types.get(rand));
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
