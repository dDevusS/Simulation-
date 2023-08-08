package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import creatures.herbivore.Cattle;
import creatures.predator.Tiger;
import items.plant.Grass;

public abstract class UserActions {
	private static Random random = new Random();

	public static void createNewCattle(Integer number, Simulation world) {
		List<Coordinate> listOfEmptyCell = findEmptyCell(world);

		while (number > 0 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			Coordinate randomEmptyCell = listOfEmptyCell.get(random.nextInt(0, listOfEmptyCell.size()));

			if (world.isCellEmpty(randomEmptyCell)) {
				world.getMap().put(randomEmptyCell, Cattle.getCattle(randomEmptyCell.getX(), randomEmptyCell.getY()));
				number--;
			}
		}
	}

	public static void createNewTiger(Integer number, Simulation world) {
		List<Coordinate> listOfEmptyCell = findEmptyCell(world);

		while (number > 0 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			Coordinate randomEmptyCell = listOfEmptyCell.get(random.nextInt(0, listOfEmptyCell.size()));

			if (world.isCellEmpty(randomEmptyCell)) {
				world.getMap().put(randomEmptyCell, Tiger.getWolf(randomEmptyCell.getX(), randomEmptyCell.getY()));
				number--;
			}
		}
	}

	public static void createNewGrass(Integer number, Simulation world) {
		List<Coordinate> listOfEmptyCell = findEmptyCell(world);

		while (number > 0 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			Coordinate randomEmptyCell = listOfEmptyCell.get(random.nextInt(0, listOfEmptyCell.size()));

			if (world.isCellEmpty(randomEmptyCell)) {
				world.getMap().put(randomEmptyCell, Grass.getGrass(randomEmptyCell.getX(), randomEmptyCell.getY()));
				number--;
			}
		}
	}

	private static List<Coordinate> findEmptyCell(Simulation world) {
		List<Coordinate> listOfEmptyCell = new ArrayList<>();

		for (int y = 0; y < world.getHeight(); y++) {

			for (int x = 0; x < world.getWidth(); x++) {

				if (world.isCellEmpty(Coordinate.doCoordinate(x, y))) {
					listOfEmptyCell.add(Coordinate.doCoordinate(x, y));
				}
			}
		}
		return listOfEmptyCell;
	}
}
