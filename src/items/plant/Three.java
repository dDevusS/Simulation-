package items.plant;

import java.util.Random;

import items.food.Orange;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Simulation;

public class Three extends Plant {
	
	public Three(Integer x, Integer y) {
		Random random=new Random();
		coordinate=new Coordinate(x, y);
		timeOfLife=random.nextInt(1, 20);
		setMapSimbol("\u001B[32m🌳\u001B[0m");
	}
	
	public static Three getThree(Integer x, Integer y) {
		return new Three(x, y);
	}
	
	@Override
	public void doAction(Simulation world) {
		timeOfLife++;
		if (timeOfLife%10==0) {
			reproduceApple(world);
		}
		if (timeOfLife>100) {
			timeOfLife=1;
		}
	}
	
	private void reproduceApple(Simulation world) {
		Coordinate cellForNewApple=Pathfinder.getClosedEmptyRandomCell(coordinate, world);
		if (cellForNewApple!=null)	{
			world.getMap().put(cellForNewApple, Orange.getApple(cellForNewApple));
		}
	}
}
