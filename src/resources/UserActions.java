package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import creatures.herbivore.Cattle;
import creatures.predator.Tiger;
import items.plant.Grass;

import static resources.WorldRender.render;

public abstract class UserActions {
	private static final Random RANDOM = new Random();
	private static final Scanner SCANNER = new Scanner(System.in);

	public static void doUserActions(World world) {
		boolean pause = true;

		while (pause) {

			switch (SCANNER.nextLine()) {
				case "1":
					doTurn(world);
					render(world);
					break;
				case "2":
					if (world.getMap().size()
							>= (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
						System.out.println("В мире недостаточно места");
					}
					else {
						UserActions.createNewCattle(5, world);
						render(world);
					}
					break;
				case "3":
					if (world.getMap().size()
							>= (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
						System.out.println("В мире недостаточно места");
					}
					else {
						UserActions.createNewTiger(5, world);
						render(world);
					}
					break;
				case "4":
					if (world.getMap().size()
							>= (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
						System.out.println("В мире недостаточно места");
					}
					else {
						UserActions.createNewGrass(5, world);
						render(world);
					}
					break;
				case "5":
					resumeSimulation(world);
					pause = false;
					break;
				case "6":
					pause(world);
					pause = false;
					break;
				default:
					System.out.println("Введенная команда неверная");
					render(world);
					break;
			}
		}
	}

	public static void pause(World world) {
		world.isRun = false;
	}

	public static void resumeSimulation(World world) {
		world.isRun = true;

		synchronized (world.getLock()) {
			world.getLock().notify();
		}
	}

	public static void doTurn(World world) {

		for (int y = 0; y < world.getHeight(); y++) {

			for (int x = 0; x < world.getWidth(); x++) {

				if (!world.isCellEmpty(Coordinate.doCoordinate(x, y))) {
					world.getMap().get(Coordinate.doCoordinate(x, y)).doAction(world);
				}
			}
		}

		world.increaseGeneration();

		if (Grass.quantityOfGrass < 10 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			UserActions.createNewGrass(5, world);
		}
	}

	public static void createNewCattle(Integer number, World world) {
		List<Coordinate> listOfEmptyCell = findEmptyCell(world);

		while (number > 0 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			Coordinate randomEmptyCell = listOfEmptyCell.get(RANDOM.nextInt(0, listOfEmptyCell.size()));

			if (world.isCellEmpty(randomEmptyCell)) {
				world.getMap().put(randomEmptyCell, Cattle.getCattle(randomEmptyCell.getX(), randomEmptyCell.getY()));
				world.setQuantityOfHerbivore(world.getQuantityOfHerbivore() + 1);
				number--;
			}
		}
	}

	public static void createNewTiger(Integer number, World world) {
		List<Coordinate> listOfEmptyCell = findEmptyCell(world);

		while (number > 0 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			Coordinate randomEmptyCell = listOfEmptyCell.get(RANDOM.nextInt(0, listOfEmptyCell.size()));

			if (world.isCellEmpty(randomEmptyCell)) {
				world.getMap().put(randomEmptyCell, Tiger.getWolf(randomEmptyCell.getX(), randomEmptyCell.getY()));
				world.setQuantityOfPredator(world.getQuantityOfPredator() + 1);
				number--;
			}
		}
	}

	public static void createNewGrass(Integer number, World world) {
		List<Coordinate> listOfEmptyCell = findEmptyCell(world);

		while (number > 0 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			Coordinate randomEmptyCell = listOfEmptyCell.get(RANDOM.nextInt(0, listOfEmptyCell.size()));

			if (world.isCellEmpty(randomEmptyCell)) {
				world.getMap().put(randomEmptyCell, Grass.getGrass(randomEmptyCell.getX(), randomEmptyCell.getY()));
				number--;
			}
		}
	}

	private static List<Coordinate> findEmptyCell(World world) {
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
