package items.plant;

import java.util.Random;

import items.food.Orange;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Sprites;
import resources.World;

public class Three extends Plant {

	public Three(Integer x, Integer y) {
		Random random = new Random();
		coordinate = new Coordinate(x, y);
		timeOfLife = random.nextInt(1, 20);
		setSprite(Sprites.TREE);
	}

	public static Three getThree(Integer x, Integer y) {
		return new Three(x, y);
	}

	@Override
	public void doAction(World world) {
		timeOfLife++;

		if (timeOfLife % 10 == 0) {
			reproduceApple(world);
		}

		if (timeOfLife > 100) {
			timeOfLife = 1;
		}
	}

	private void reproduceApple(World world) {
		Coordinate cellForNewApple = Pathfinder.getClosedEmptyRandomCell(coordinate, world);

		if (cellForNewApple != null) {
			world.getMap().put(cellForNewApple, Orange.getApple(cellForNewApple));
		}
	}
}
