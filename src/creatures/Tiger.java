package creatures;

import java.util.Random;

import resources.Coordinate;
import resources.Pathfinder;
import resources.Simulation;

public class Tiger extends Predator {

	public Tiger(Integer x, Integer y) {
		setAge(0);
		setCoordinate(x, y);
		setMapSimbol("\u001B[35m🐅\u001B[0m");
		setName(CreaturesNames.WOLF);
		setSpeed(3);
		setTimeToReproduce(random.nextInt(3, 8));
		setVolueOfHunger(50);
		setVolueOfLife(15);
		setAttackPower(10);
	}
	
	public static Tiger getWolf(Integer x, Integer y) {
		Predator.quantityOfPredator++;
		return new Tiger(x, y);
	}
	
	@Override
	public void reproduce(Simulation world) {
		Random random=new Random();
		for (int numberNewWolf=random.nextInt(1, 3); numberNewWolf>0; numberNewWolf--) {
			Coordinate cellForNewWolf=Pathfinder.getClosedEmptyRandomCell(coordinate, world);
			if (cellForNewWolf!=null) {
				world.getMap().put(cellForNewWolf, getWolf(cellForNewWolf.x, cellForNewWolf.y));
			}
		}
		setTimeToReproduce(5);
		setVolueOfHunger(volueOfHunger-40);
	}
}
