package creatures;

import java.util.Random;

import resources.Coordinate;
import resources.Pathfinder;
import resources.WorldMapNew;

public class Cattle extends Herbivore {
	Random random=new Random();

	public Cattle(Integer x, Integer y) {
		setAge(0);
		setCoordinate(x, y);
		setMapSimbol("🐂");
		setName(CreaturesNames.CATTLE);
		setSpeed(2);
		setTimeToReproduce(random.nextInt(5, 25));
		setVolueOfHunger(50);
		setVolueOfLife(20);
		setAttackPower(2);
	}
	
	public static Cattle getCattle(Integer x, Integer y) {
		return new Cattle(x, y);
	}
	
	@Override
	public void reproduce(WorldMapNew world) {
		Random random=new Random();
		for (int numberNewCattle=random.nextInt(1, 3); numberNewCattle>0; numberNewCattle--) {
			Coordinate cellForNewCattle=Pathfinder.getClosedEmptyRandomCell(coordinate, world);
			if (cellForNewCattle!=null) {
				world.getMap().put(cellForNewCattle, getCattle(cellForNewCattle.x, cellForNewCattle.y));
				setTimeToReproduce(15);
			}
		}
	}
}
