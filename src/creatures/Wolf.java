package creatures;

import java.util.Random;

import resources.Coordinate;
import resources.Pathfinder;
import resources.WorldMapNew;

public class Wolf extends Predator {

	public Wolf(Integer x, Integer y) {
		setAge(0);
		setCoordinate(x, y);
		setMapSimbol("\u001B[35m🐅\u001B[0m");
		setName(CreaturesNames.WOLF);
		setSpeed(3);
		setTimeToReproduce(random.nextInt(5, 35));
		setVolueOfHunger(50);
		setVolueOfLife(10);
		setAttackPower(10);
	}
	
	public static Wolf getWolf(Integer x, Integer y) {
		return new Wolf(x, y);
	}
	
	@Override
	public void reproduce(WorldMapNew world) {
		Random random=new Random();
		for (int numberNewWolf=random.nextInt(1, 3); numberNewWolf>0; numberNewWolf--) {
			Coordinate cellForNewWolf=Pathfinder.getClosedEmptyRandomCell(coordinate, world);
			if (cellForNewWolf!=null) {
				world.getMap().put(cellForNewWolf, getWolf(cellForNewWolf.x, cellForNewWolf.y));
				setTimeToReproduce(20);
			}
		}
	}
}
