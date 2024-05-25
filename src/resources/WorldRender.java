package resources;

public abstract class WorldRender {

	public static void render(World world) {
		int height = world.getHeight();

		if (world.getHeight() <= 12) {
			height = world.getHeight() + (11 - world.getHeight());
		}

		for (int ySize = 0; ySize <= height; ySize++) {

			for (int xSize = 0; xSize <= world.getWidth(); xSize++) {

				if (world.getMap().containsKey(Coordinate.createCoordinates(xSize, ySize))) {
					System.out.print(world.getMap().get(Coordinate.createCoordinates(xSize, ySize)));
				}
				else if (world.getHeight() < 12 && ySize > world.getHeight()) {
					System.out.print(Sprites.EMPTY_CELL);
				}
				else {
					System.out.print(Sprites.EMPTY_CELL);
				}
			}

			printMenu(ySize, world);
		}
		System.out.print("\n");
	}

	private static void printMenu(int yCoordinate, World world) {
		switch (yCoordinate) {
			case 0:
				System.out.print("Поколение " + world.getGeneration());
				System.out.print(!world.isRun ? "  ПАУЗА" : " ");
				break;
			case 1:
				System.out.print(Sprites.TIGER + " - тигр  " + Sprites.CATTLE + " - бык  " + Sprites.EMPTY_CELL + " - пустая клетка");
				break;
			case 2:
				System.out.print(Sprites.ROCK + " - камень  " + Sprites.TREE + " - дерево  " + Sprites.ORANGE + " - апельсин");
				break;
			case 3:
				System.out.print(
						Sprites.GRASS_STAGE_1 + " " + Sprites.GRASS_STAGE_2 + " " + Sprites.GRASS_STAGE_3
								+ " - трава(от побега до цветка)  " + Sprites.MEAT + " - мясо");
				break;
			case 4:
				System.out.print("Количесво травоядных: " + world.getQuantityOfHerbivore());
				break;
			case 5:
				System.out.print("Количесво хищников: " + world.getQuantityOfPredator());
				break;
			case 6:
				System.out.print(world.isRun ? "1 - поставить на паузу." : "1 - следующее поколение.");
				break;
			case 7:
				System.out.print(world.isRun ? "2 - выйти в главное меню." : "2 - добавить травоядных");
				break;
			case 8:
				System.out.print(world.isRun ? "" : "3 - добавить хищников");
				break;
			case 9:
				System.out.print(world.isRun ? "" : "4 - добавить травы");
				break;
			case 10:
				System.out.print(world.isRun ? "" : "5 - возобновить симуляцию.");
				break;
			case 11:
				System.out.print(world.isRun ? "" : "6 - выйти в главное меню.");
				break;
		}
		System.out.print("\n");
	}

}
