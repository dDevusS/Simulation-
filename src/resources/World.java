package resources;

import items.Wall;

import java.util.HashMap;

import static resources.WorldRender.render;
import static resources.UserActions.*;

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
		createNewEntities(newWorld.countQuantityWithCoefficient(COEFFICIENT_ROCKS_CREATING), newWorld, Sprites.ROCK);
		createNewEntities(newWorld.countQuantityWithCoefficient(COEFFICIENT_TREES_CREATING), newWorld, Sprites.TREE);
		createNewEntities(newWorld.countQuantityWithCoefficient(COEFFICIENT_GRASS_CREATING), newWorld, Sprites.GRASS_STAGE_1);
		createNewEntities(newWorld.countQuantityWithCoefficient(COEFFICIENT_CATTLE_CREATING), newWorld, Sprites.CATTLE);
		createNewEntities(newWorld.countQuantityWithCoefficient(COEFFICIENT_TIGERS_CREATING), newWorld, Sprites.TIGER);
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
					this.getMap().put(new Coordinate(xSize, ySize), Wall.getWall());
				}
			}
		}
	}

	private int countQuantityWithCoefficient(int coefficient) {
		return height * width / coefficient;
	}

	public synchronized boolean isRun() {
		return isRun;
	}

	public HashMap<Coordinate, Entity> getMap() {
		return map;
	}

	public boolean isEmptyCell(Coordinate coordinate) {
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

	public void increaseQuantityOfHerbivore() {
		quantityOfHerbivore++;
	}

	public void decreaseQuantityOfHerbivore() {
		quantityOfHerbivore--;
	}

	public int getQuantityOfPredator() {
		return quantityOfPredator;
	}

	public void increaseQuantityOfPredator() {
		quantityOfPredator++;
	}

	public void decreaseQuantityOfPredator() {
		quantityOfPredator--;
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
