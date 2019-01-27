import org.junit.*;
import org.junit.jupiter.api.Test;

import javafx.scene.layout.StackPane;

public class GameTest {
	
	Player player = new Player();
	EnemyFish target = new EnemyFish(FishType.CATFISH);
	FishController fController = new FishController();
	
	@Test
	public void testCanEnemyDefeatsPlayer() {
		Assert.assertEquals(true, player.canPlayerEatEnemy(target));
	}
	
	@Test
	public void testEnemyInitializationOffScreen() {
		Assert.assertEquals(false, target.isOffscreen(target.getLocationX()));
	}
	
	@Test
	public void testCollision() {
		target.updateLocation(0, 0);
		Assert.assertEquals(true, target.isColliding(player));
	}

	@Test
	public void testFishDeath() {
		if(player.canPlayerEatEnemy(target)) {
			target.setVisible(false);
		}
		
		Assert.assertEquals(false, target.isVisible());
	}
	
	
	
}

