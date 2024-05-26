package creatures;

import java.util.ArrayList;
import java.util.List;

import items.food.Meat;
import items.food.Orange;
import items.plant.Grass;
import resources.Coordinate;
import resources.Entity;
import resources.Pathfinder;
import resources.World;

import static utils.Utils.RANDOM;

public abstract class Intention {

	private static final int COEFFICIENT_MOTIVATION = 21;
	private static final int LEVEL_OF_CRITICAL_HUNGER = 50;

	public static TypeIntention makeIntention(World world, Coordinate coordinate) {
		Creatures creature = (Creatures) world.getMap().get(coordinate);

		if (creature.valueOfHunger + RANDOM.nextInt(0, COEFFICIENT_MOTIVATION) < LEVEL_OF_CRITICAL_HUNGER) {
			return TypeIntention.WANT_EAT;
		}
		else if (creature.timeToReproduce <= 0
				&& Pathfinder.getClosedEmptyRandomCell(coordinate, world) != null) {
			return TypeIntention.WANT_REPRODUCE;
		}
		else {
			return TypeIntention.WANT_STROLL;
		}
	}

	enum TypeIntention {

		WANT_EAT, WANT_REPRODUCE, WANT_STROLL
	}

	public static Coordinate findFood(World world, Coordinate coordinate) {
		List<Coordinate> listOfGoals;
		Creatures creature = (Creatures) world.getMap().get(coordinate);

		if (creature instanceof Herbivore) {
			listOfGoals = findHerb(world, coordinate);
		}
		else {
			listOfGoals = findMeat(world, coordinate);
		}

		if (!listOfGoals.isEmpty()) {
			return listOfGoals.get(RANDOM.nextInt(0, listOfGoals.size()));
		}
		else {
			return null;
		}
	}

	private static List<Coordinate> findMeat(World world, Coordinate coordinate) {
		List<Coordinate> listOfGoals = new ArrayList<>();
		// TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding = 3;
		int modifierFinding = 0;

		while (listOfGoals.isEmpty() && radiusFinding > 0) {

			for (int y = -1 - modifierFinding; y < 2 + modifierFinding; y++) {

				for (int x = -1 - modifierFinding; x < 2 + modifierFinding; x++) {

					if (!world.isEmptyCell(coordinate.shiftCell(x, y))) {

						if (world.getMap().get(coordinate.shiftCell(x, y)) instanceof Meat) {
							listOfGoals.add(coordinate.shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--;
			modifierFinding++;
		}
		return listOfGoals;
	}

	private static List<Coordinate> findHerb(World world, Coordinate coordinate) {
		List<Coordinate> listOfGoals = new ArrayList<>();
		// TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding = 10;
		int modifierFinding = 0;

		while (listOfGoals.isEmpty() && radiusFinding > 0) {

			for (int y = -1 - modifierFinding; y < 2 + modifierFinding; y++) {

				for (int x = -1 - modifierFinding; x < 2 + modifierFinding; x++) {
					Coordinate shiftedCoordinate = coordinate.shiftCell(x, y);

					if (!world.isEmptyCell(shiftedCoordinate)) {
						Entity maybeGoal = world.getMap().get(shiftedCoordinate);

						if (maybeGoal instanceof Orange || maybeGoal instanceof Grass) {
							listOfGoals.add(shiftedCoordinate);
						}
					}
				}
			}
			radiusFinding--;
			modifierFinding++;
		}
		return listOfGoals;
	}
}
