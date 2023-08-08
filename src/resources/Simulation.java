package resources;

import java.util.HashMap;
import java.util.Random;

import creatures.Herbivore;
import creatures.Predator;
import items.MapWall;
import items.Rock;
import items.plant.Grass;
import items.plant.Three;

public class Simulation {

	private HashMap<Coordinate, Entity> map;
	private int worldWidth;
	private int worldHeight;
	private int generation = 0;

	public Simulation(int worldWidth, int worldHeight) {
		this.map = new HashMap<Coordinate, Entity>();
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
	}

	public void doTurn() {
		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				if (!this.isCellEmpty(Coordinate.doCoordinate(x, y))) {
					this.getMap().get(Coordinate.doCoordinate(x, y)).doAction(this);
				}
			}
		}
		generation++;
		if (Grass.quantityOfGrass < 10 && map.size() < (this.getHeight() + 1) * (this.getWidth() + 1) - 5) {
			UserActions.createNewGrass(5, this);
		}
	}

	public void doRendering(boolean isRun) {
		// ➖⬛⬜🟩🟨🟧🟥🟩🟦🟪🟫🔘🔴🟠⚫🟤🟣🔵⚪〰️
		// 🐅🐆🐂🐃🐄🐖🐐🐕🐒🦍🐮🐷🐀🐇🦖🥓🥩🍗🍖🍊🍎
		int height = this.worldHeight;
		if (this.worldHeight <= 12) {
			height = worldHeight + (11 - this.worldHeight);
		}

		for (int ySize = 0; ySize <= height; ySize++) {
			for (int xSize = 0; xSize <= this.getWidth(); xSize++) {
				if (this.getMap().containsKey(Coordinate.doCoordinate(xSize, ySize))) {
					System.out.print(this.getMap().get(Coordinate.doCoordinate(xSize, ySize)).getMapSimbol());
				} else if (this.worldHeight < 12 && ySize > this.worldHeight) {
					System.out.print("🟫");
				} else {
					System.out.print("🟫");
				}
			}
			switch (ySize) {
			case 0:
				System.out.print("Поколение " + generation);
				System.out.print(!isRun ? "  ПАУЗА" : " ");
				break;
			case 1:
				System.out.print("\u001B[35m🐅\u001B[0m - тигр  🐂 - бык  🟫 - пустая клетка");
				break;
			case 2:
				System.out.print("🗻 - камень  \u001B[32m🌳\u001B[0m - дерево  \u001B[31m🍊\u001B[0m - апельсин");
				break;
			case 3:
				System.out.print(
						"\u001B[33m🌱 🌾 🌻\u001B[0m - трава(от побега до цветка)  \u001B[31m🥩\u001B[0m - мясо");
				break;
			case 4:
				System.out.print("Количесво травоядных: " + Herbivore.quantityOfHerbivore);
				break;
			case 5:
				System.out.print("Количесво хищников: " + Predator.quantityOfPredator);
				break;
			case 6:
				System.out.print(isRun ? "1 - поставить на паузу." : "1 - следующее поколение.");
				break;
			case 7:
				System.out.print(isRun ? "2 - выйти в главное меню." : "2 - добавить травоядных");
				break;
			case 8:
				System.out.print(isRun ? "" : "3 - добавить хищников");
				break;
			case 9:
				System.out.print(isRun ? "" : "4 - добавить травы");
				break;
			case 10:
				System.out.print(isRun ? "" : "5 - возобновить симуляцию.");
				break;
			case 11:
				System.out.print(isRun ? "" : "6 - выйти в главное меню.");
				break;
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public static Simulation createWorld(Integer worldWidth, Integer worldHeight) {
		Simulation world = new Simulation(worldWidth, worldHeight);
		Herbivore.quantityOfHerbivore = 0;
		Predator.quantityOfPredator = 0;
		Grass.quantityOfGrass = 0;

		for (int ySize = 0; ySize <= worldHeight; ySize++) {
			for (int xSize = 0; xSize <= worldWidth; xSize++) {
				if (xSize == 0 || ySize == 0 || xSize == worldWidth || ySize == worldHeight) {
					world.getMap().put(new Coordinate(xSize, ySize), MapWall.getMapWall(xSize, ySize));
				}
			}
		}
		return world;
	}

	public void createItems() {
		int numberRocks = worldHeight * worldWidth / 17;
		int numberThrees = worldHeight * worldWidth / 20;
		int numberGrass = worldHeight * worldWidth / 4;
		Random random = new Random();

		while (numberRocks > 0) {
			int x = random.nextInt(worldWidth);
			int y = random.nextInt(worldHeight);
			if (isCellEmpty(new Coordinate(x, y))) {
				map.put(new Coordinate(x, y), Rock.getRock(x, y));
				numberRocks--;
			}
		}

		while (numberThrees > 0) {
			int x = random.nextInt(worldWidth);
			int y = random.nextInt(worldHeight);
			if (isCellEmpty(new Coordinate(x, y))) {
				map.put(new Coordinate(x, y), Three.getThree(x, y));
				numberThrees--;
			}
		}
		UserActions.createNewGrass(numberGrass, this);
	}

	public void createCreatures() {
		int numberCattle = worldHeight * worldWidth / 10;
		int numberTiger = worldHeight * worldWidth / 25;

		UserActions.createNewCattle(numberCattle, this);
		UserActions.createNewTiger(numberTiger, this);
	}

	public int getWidth() {
		return worldWidth;
	}

	public int getHeight() {
		return worldHeight;
	}

	public HashMap<Coordinate, Entity> getMap() {
		return map;
	}

	public boolean isCellEmpty(Coordinate coordinate) {
		return !map.containsKey(coordinate);
	}
}
