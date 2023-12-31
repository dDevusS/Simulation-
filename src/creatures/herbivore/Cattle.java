package creatures.herbivore;

import java.util.Random;

import creatures.CreaturesNames;
import creatures.Herbivore;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Simulation;

public class Cattle extends Herbivore {
	Random random = new Random();

	public Cattle(Integer x, Integer y) {
		setAge(0);
		setCoordinate(x, y);
		setMapSimbol("🐂");
		setName(CreaturesNames.CATTLE);
		setSpeed(2);
		setTimeToReproduce(random.nextInt(3, 6));
		setValueOfHunger(50);
		setValueOfLife(20);
		setAttackPower(2);
	}

	public static Cattle getCattle(Integer x, Integer y) {
		Herbivore.quantityOfHerbivore++;
		return new Cattle(x, y);
	}

	@Override
	public void reproduce(Simulation world) {
		Random random = new Random();

		for (int numberNewCattle = random.nextInt(1, 4); numberNewCattle > 0; numberNewCattle--) {
			Coordinate cellForNewCattle = Pathfinder.getClosedEmptyRandomCell(coordinate, world);

			if (cellForNewCattle != null) {
				world.getMap().put(cellForNewCattle, getCattle(cellForNewCattle.getX(), cellForNewCattle.getY()));
			}
		}
		setTimeToReproduce(random.nextInt(2, 5));
		setValueOfHunger(valueOfHunger - 40);
	}
}
