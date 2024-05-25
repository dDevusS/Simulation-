package resources;

import items.MapWall;
import items.Rock;
import items.plant.Three;

import java.util.HashMap;

import static resources.WorldRender.render;
import static resources.UserActions.*;
import static utils.Utils.RANDOM;

public class World implements Runnable {

	public boolean isRun = true;
	private final Object lock = new Object();

	private final int height;
	private final int width;
	private final HashMap<Coordinate, Entity> map = new HashMap<>();;

	private int generation;
	private int quantityOfHerbivore;
	private int quantityOfPredator;

	private final static int COEFFICIENT_ROCKS_CREATING = 17;
	private final static int COEFFICIENT_TREES_CREATING = 20;
	private final static int COEFFICIENT_GRASS_CREATING = 4;
	private final static int COEFFICIENT_CATTLE_CREATING = 10;
	private final static int COEFFICIENT_TIGERS_CREATING = 25;
	private final static int TIME_BETWEEN_WORLD_INTERACTION = 2000;

	private World(int worldHeight, int worldWidth) {
		this.height = worldHeight;
		this.width = worldWidth;
		this.generation = 0;
		this.quantityOfHerbivore = 0;
		this.quantityOfPredator = 0;
	}

	public static World createNewWorld(int worldHeight, int worldWidth) {
		World newWorld = new World(worldHeight, worldWidth);
		newWorld.createWalls();
		newWorld.createRocks();
		newWorld.createTrees();
		newWorld.createGrass();
		newWorld.createCattle();
		newWorld.createTigers();
		return newWorld;
	}

	public void run() {
		render(this);

		try {
			Thread.sleep(TIME_BETWEEN_WORLD_INTERACTION);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {

			synchronized (lock) {

				while (!isRun) {

					try {
						lock.wait();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			doTurn(this);
			render(this);

			try {
				Thread.sleep(TIME_BETWEEN_WORLD_INTERACTION);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void createWalls() {
		for (int ySize = 0; ySize <= height; ySize++) {

			for (int xSize = 0; xSize <= width; xSize++) {

				if (xSize == 0 || ySize == 0 || xSize == width || ySize == height) {
					this.getMap().put(new Coordinate(xSize, ySize), MapWall.getMapWall());
				}
			}
		}
	}

	private void createRocks() {
		int numberRocks = height * width / COEFFICIENT_ROCKS_CREATING;

		while (numberRocks > 0) {
			int y = RANDOM.nextInt(height);
			int x = RANDOM.nextInt(width);

			if (isCellEmpty(new Coordinate(x, y))) {
				map.put(new Coordinate(x, y), Rock.getRock());
				numberRocks--;
			}
		}
	}

	private void createTrees() {
		int numberThrees = height * width / COEFFICIENT_TREES_CREATING;

		while (numberThrees > 0) {
			int y = RANDOM.nextInt(height);
			int x = RANDOM.nextInt(width);

			if (isCellEmpty(new Coordinate(x, y))) {
				map.put(new Coordinate(x, y), Three.getThree(x, y));
				numberThrees--;
			}
		}
	}

	private void createGrass() {
		int numberGrass = height * width / COEFFICIENT_GRASS_CREATING;
		UserActions.createNewGrass(numberGrass, this);
	}

	private void createCattle() {
		int numberCattle = height * width / COEFFICIENT_CATTLE_CREATING;
		UserActions.createNewCattle(numberCattle, this);
	}

	private void createTigers() {
		int numberTigers = height * width / COEFFICIENT_TIGERS_CREATING;
		UserActions.createNewTiger(numberTigers, this);
	}

	public synchronized boolean isRun() {
		return isRun;
	}

	public HashMap<Coordinate, Entity> getMap() {
		return map;
	}

	public boolean isCellEmpty(Coordinate coordinate) {
		return !map.containsKey(coordinate);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getQuantityOfHerbivore() {
		return quantityOfHerbivore;
	}

	public void setQuantityOfHerbivore(int quantityOfHerbivore) {
		this.quantityOfHerbivore = quantityOfHerbivore;
	}

	public int getQuantityOfPredator() {
		return quantityOfPredator;
	}

	public void setQuantityOfPredator(int quantityOfPredator) {
		this.quantityOfPredator = quantityOfPredator;
	}

	public int getGeneration() {
		return generation;
	}

	public void increaseGeneration() {
		this.generation++;
	}

	public Object getLock() {
		return lock;
	}
}
