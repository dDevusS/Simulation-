package creatures.predator;

import java.util.Random;

import creatures.CreaturesNames;
import creatures.Predator;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Simulation;

public class Tiger extends Predator {

	public Tiger(Integer x, Integer y) {
		Random random=new Random();
		setAge(0);
		setCoordinate(x, y);
		setMapSimbol("\u001B[35m🐅\u001B[0m");
		setName(CreaturesNames.TIGER);
		setSpeed(3);
		setTimeToReproduce(random.nextInt(4, 8));
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
				world.getMap().put(cellForNewWolf, getWolf(cellForNewWolf.getX(), cellForNewWolf.getY()));
			}
		}
		setTimeToReproduce(random.nextInt(4, 8));
		setVolueOfHunger(volueOfHunger-40);
	}
}
