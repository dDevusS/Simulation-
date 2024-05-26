package items.plant;

import items.food.Orange;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Sprites;
import resources.World;

import static utils.Utils.RANDOM;

public class Tree extends Plant {

	private static final int REPRODUCE_TIME = 10;

	public Tree() {
        super(Sprites.TREE);
        timeOfLife = RANDOM.nextInt(1, 20);
		setSprite(Sprites.TREE);
	}

	public static Tree getTree() {
		return new Tree();
	}

	@Override
	public void doAction(World world, Coordinate coordinate) {
		timeOfLife++;

		if (timeOfLife % REPRODUCE_TIME == 0) {
			reproduceOrange(world, coordinate);
			timeOfLife = 1;
		}
	}

	private void reproduceOrange(World world, Coordinate coordinate) {
		Coordinate randomClosedEmptyCell = Pathfinder.getClosedEmptyRandomCell(coordinate, world);

		if (randomClosedEmptyCell != null) {
			world.getMap().put(randomClosedEmptyCell, Orange.getOrange());
		}
	}
}
