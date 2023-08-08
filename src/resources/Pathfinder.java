package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import creatures.Creatures;

public class Pathfinder {

	public static Coordinate findPath(Coordinate goal, Coordinate creature, Simulation world) {
		List<Coordinate> avaibleCells = new ArrayList<>();
		List<Integer> listOfCellsPrice = new ArrayList<>();

		if (getClosedEmptyRandomCell(creature, world) == null) {
			return null;
		}

		for (int y = -1; y < 2; y++) {

			for (int x = -1; x < 2; x++) {

				if (world.isCellEmpty(creature.shiftCell(x, y))) {
					int extra = (Math.abs(x) == 1 && Math.abs(y) == 1) ? 14 : 10;
					listOfCellsPrice.add(extra + calculateSteps(goal, creature.shiftCell(x, y)));
					avaibleCells.add(creature.shiftCell(x, y));
				}
			}
		}

		if (!listOfCellsPrice.isEmpty() && !avaibleCells.isEmpty()) {
			int lowestCellPriceIndex = 0;
			int lowestCellPrice = listOfCellsPrice.get(lowestCellPriceIndex);

			for (int i = 1; i < listOfCellsPrice.size(); i++) {

				if (listOfCellsPrice.get(i) < lowestCellPrice) {
					lowestCellPriceIndex = i;
					lowestCellPrice = listOfCellsPrice.get(i);
				}
			}
			return avaibleCells.get(lowestCellPriceIndex);
		}
		return null;
	}

	public static Coordinate getClosedEmptyRandomCell(Coordinate creature, Simulation world) {
		Random random = new Random();
		List<Coordinate> listOfEmptyCells = new ArrayList<>();

		for (int y = -1; y < 2; y++) {

			for (int x = -1; x < 2; x++) {

				if (world.isCellEmpty(creature.shiftCell(x, y))) {
					listOfEmptyCells.add(creature.shiftCell(x, y));
				}
			}
		}

		if (!listOfEmptyCells.isEmpty()) {
			return listOfEmptyCells.get(random.nextInt(0, listOfEmptyCells.size()));
		}
		return null;
	}

	public static boolean isClosedCell(Coordinate goalCell, Creatures creature, Simulation world) {
		return Math.abs(goalCell.getX() - creature.getCoordinate().getX()) <= 1
				&& Math.abs(goalCell.getY() - creature.getCoordinate().getY()) <= 1;
	}

	private static int calculateSteps(Coordinate goal, Coordinate closedEmptyCell) {
		return (Math.abs(goal.getX() - closedEmptyCell.getX()) + Math.abs(goal.getY() - closedEmptyCell.getY())) * 10;
	}
}
