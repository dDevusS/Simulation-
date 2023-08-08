import java.util.Scanner;

import resources.UserActions;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static int height;
	private static int width;
	private static WorldCreator world;

	public static void main(String[] args) {
		while (true) {
			viewMenu();

			while (world.isRun()) {
				switch (scanner.nextLine()) {
				case "1":
					world.pauseSimulation();
					world.getWorld().doRendering(world.isRun);
					doUserActions();
					break;
				case "2":
					world.pauseSimulation();
					break;
				}
			}
		}
	}

	private static void viewMenu() {
		System.out.println("""

				Симуляция.
				1-Начало симуляции.
				2-Выход.

				Для начала симуляции введите 1. Для выхода введите 2.
				""");

		switch (scanner.nextLine()) {
		case "1":
			System.out.println("""
					Укажите размеры мира симуляции.
					Введите длинну мира по оси Y, затем ширину мира по оси X.
					*Созданный мир должен быть не меньше, чем 10х10 и не больше, чем 50х50.
					*Не рекомендуется создавать мир больше 30х30.

					""");
			while (true) {
				System.out.println("Длинна мира по оси Y: ");
				String number = scanner.nextLine();
				if (containsInt(number) && Integer.valueOf(number) >= 10 && Integer.valueOf(number) <= 50) {
					height = Integer.valueOf(number);
					break;
				} else {
					System.out.println("Выдолжны ввести целое число в диапазоне от 10 до 50.");
				}
			}
			while (true) {
				System.out.println("Ширина мира по оси X: ");
				String number = scanner.nextLine();
				if (containsInt(number) && Integer.valueOf(number) >= 10 && Integer.valueOf(number) <= 50) {
					width = Integer.valueOf(number);
					break;
				} else {
					System.out.println("Выдолжны ввести целое число в диапазоне от 10 до 50.");
				}
			}

			world = new WorldCreator(height, width);
			Thread worldThread = new Thread(world);
			worldThread.start();
			break;
		case "2":
			System.exit(0);
			break;
		default:
			System.out.println("Вы должны ввести число 1 или 2.");
			break;
		}
	}

	private static void doUserActions() {
		boolean pause = true;
		while (pause) {
			switch (scanner.nextLine()) {
			case "1":
				world.getWorld().doTurn();
				world.getWorld().doRendering(world.isRun);
				break;
			case "2":
				if (world.getWorld().getMap()
						.size() >= (world.getWorld().getHeight() + 1) * (world.getWorld().getWidth() + 1) - 5) {
					System.out.println("В мире недостаточно места");
				} else {
					UserActions.createNewCattle(5, world.getWorld());
					world.getWorld().doRendering(world.isRun);
				}
				break;
			case "3":
				if (world.getWorld().getMap()
						.size() >= (world.getWorld().getHeight() + 1) * (world.getWorld().getWidth() + 1) - 5) {
					System.out.println("В мире недостаточно места");
				} else {
					UserActions.createNewTiger(5, world.getWorld());
					world.getWorld().doRendering(world.isRun);
				}
				break;
			case "4":
				if (world.getWorld().getMap()
						.size() >= (world.getWorld().getHeight() + 1) * (world.getWorld().getWidth() + 1) - 5) {
					System.out.println("В мире недостаточно места");
				} else {
					UserActions.createNewGrass(5, world.getWorld());
					world.getWorld().doRendering(world.isRun);
				}
				break;
			case "5":
				world.resumeSimulation();
				pause = false;
				break;
			case "6":
				world.pauseSimulation();
				pause = false;
				break;
			}
		}
	}

	private static boolean containsInt(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
