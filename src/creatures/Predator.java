package creatures;

import java.util.ArrayList;
import java.util.List;

import resources.*;

import static utils.Utils.RANDOM;

public abstract class Predator extends Creatures {

	public Predator(Sprites sprites) {
		super(sprites);
	}

	public void hunt(World world, Coordinate coordinate) {
		Coordinate preyLocation = findPrey(world, coordinate);

		if (preyLocation != null && Pathfinder.isClosedCell(preyLocation, coordinate)) {
			doAttack(preyLocation, world, coordinate);
		}
		else {
			doMove(Pathfinder.findPath(preyLocation, world, coordinate), world, coordinate);
		}
	}

	public static Coordinate findPrey(World world, Coordinate coordinate) {
		List<Coordinate> listOfGoals = new ArrayList<>();
		// TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding = 10;
		int modifierFinding = 0;

		while (listOfGoals.isEmpty() && radiusFinding > 0) {

			for (int y = -1 - modifierFinding; y < 2 + modifierFinding; y++) {

				for (int x = -1 - modifierFinding; x < 2 + modifierFinding; x++) {

					if (!world.isEmptyCell(coordinate.shiftCell(x, y))) {

						if (world.getMap().get(coordinate.shiftCell(x, y)) instanceof Herbivore) {
							listOfGoals.add(coordinate.shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--;
			modifierFinding++;
		}

		if (!listOfGoals.isEmpty()) {
			return listOfGoals.get(RANDOM.nextInt(0, listOfGoals.size()));
		}
		else {
			return null;
		}
	}

	public void doAttack(Coordinate preyLocation, World world, Coordinate coordinate) {
		Herbivore prey = (Herbivore) world.getMap().get(preyLocation);

		valueOfHealth = valueOfHealth - prey.attackPower - RANDOM.nextInt(-3, 3);
		prey.valueOfHealth -= (attackPower - RANDOM.nextInt(-1, 6));

		if (valueOfHealth <= 0) {
			die(world, coordinate);
		}

		if (prey.valueOfHealth <= 0) {
			prey.die(world, preyLocation);
		}
	}

}
