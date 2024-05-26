package resources;

import java.util.ArrayList;
import java.util.List;

import static resources.WorldRender.render;
import static utils.Utils.*;

public abstract class UserActions {

	private static final int QUANTITY_NEW_OBJECTS = 5;

	private static final String COMMAND_DO_TURN = "1";
	private static final String COMMAND_CREATE_CATTLE = "2";
	private static final String COMMAND_CREATE_TIGER = "3";
	private static final String COMMAND_CREATE_GRASS = "4";
	private static final String COMMAND_RESUME_SIMULATION = "5";
	private static final String COMMAND_PAUSE = "6";

	public static void doUserActions(World world) {
		boolean pause = true;

		while (pause) {

			switch (SCANNER.nextLine()) {
				case COMMAND_DO_TURN -> {
					doTurn(world);
					render(world);
				}
				case COMMAND_CREATE_CATTLE -> {
					createNewEntities(QUANTITY_NEW_OBJECTS, world, Sprites.CATTLE);
				}
				case COMMAND_CREATE_TIGER -> {
					createNewEntities(QUANTITY_NEW_OBJECTS, world, Sprites.TIGER);
				}
				case COMMAND_CREATE_GRASS -> {
					createNewEntities(QUANTITY_NEW_OBJECTS, world, Sprites.GRASS_STAGE_1);
				}
				case COMMAND_RESUME_SIMULATION -> {
					resumeSimulation(world);
					pause = false;
				}
				case COMMAND_PAUSE -> {
					pause(world);
					pause = false;
				}
				default -> {
					System.out.println("Введенная команда неверная");
					render(world);
				}
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
				Coordinate coordinate = Coordinate.createCoordinates(x, y);

				if (!world.isEmptyCell(coordinate)) {
					Entity entity = world.getMap().get(coordinate);

					if (entity instanceof ActionCapable lifeEntity) {
                        lifeEntity.doAction(world, coordinate);
					}
				}
			}
		}

		world.increaseGeneration();
	}

	public static void createNewEntities(int quantity, World world, Sprites sprites) {
		List<Coordinate> listOfEmptyCell = findEmptyCell(world);

		while (quantity > 0 && world.getMap().size() < (world.getHeight() + 1) * (world.getWidth() + 1) - 5) {
			Coordinate randomEmptyCell = listOfEmptyCell.get(RANDOM.nextInt(0, listOfEmptyCell.size()));

			if (world.isEmptyCell(randomEmptyCell)) {
				Entity entity = sprites.createEntity();
				world.getMap().put(randomEmptyCell, entity);
				increaseQuantityOfCreatures(world, sprites);
				quantity--;
			}
		}
	}

	public static void increaseQuantityOfCreatures(World world, Sprites sprites) {
		switch (sprites) {
			case CATTLE -> world.increaseQuantityOfHerbivore();
			case TIGER -> world.increaseQuantityOfPredator();
		}
	}

	public static void decreaseQuantityOfCreatures(World world, Sprites sprites) {
		switch (sprites) {
			case CATTLE -> world.decreaseQuantityOfHerbivore();
			case TIGER -> world.decreaseQuantityOfPredator();
		}
	}

	private static List<Coordinate> findEmptyCell(World world) {
		List<Coordinate> listOfEmptyCell = new ArrayList<>();

		for (int y = 0; y < world.getHeight(); y++) {

			for (int x = 0; x < world.getWidth(); x++) {
				Coordinate coordinate = Coordinate.createCoordinates(x, y);

				if (world.isEmptyCell(coordinate)) {
					listOfEmptyCell.add(coordinate);
				}
			}
		}
		return listOfEmptyCell;
	}
}
