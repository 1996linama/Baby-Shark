import java.awt.*;

public abstract class Fish implements Runnable {

	int speed;
	int frequency;
	int x;
	int y;
	
	public abstract int getSpeed();
	public abstract int getFrequency();
	public abstract void run();
	
	Fish() {}
	
}
