import java.util.Scanner;

import resources.World;

import static resources.UserActions.*;
import static resources.WorldRender.render;

public class Main {

	private static final Scanner SCANNER = new Scanner(System.in);
	private static World world;

	public static void main(String[] args) {

		while (true) {
			viewMenu();

			while (world.isRun()) {

				switch (SCANNER.nextLine()) {
				case "1":
					pause(world);
					render(world);
					doUserActions(world);
					break;
				case "2":
					pause(world);
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

		switch (SCANNER.nextLine()) {
		case "1":
			int height;
			int width;

			System.out.println("""
					Укажите размеры мира симуляции.
					Введите длинну мира по оси Y, затем ширину мира по оси X.
					*Созданный мир должен быть не меньше, чем 10х10 и не больше, чем 50х50.
					*Не рекомендуется создавать мир больше 30х30.

					""");
			while (true) {
				System.out.println("Длинна мира по оси Y: ");
				String number = SCANNER.nextLine();

				if (containsInt(number) && Integer.parseInt(number) >= 10 && Integer.parseInt(number) <= 50) {
					height = Integer.parseInt(number);
					break;
				}
				else {
					System.out.println("Выдолжны ввести целое число в диапазоне от 10 до 50.");
				}
			}
			while (true) {
				System.out.println("Ширина мира по оси X: ");
				String number = SCANNER.nextLine();

				if (containsInt(number) && Integer.parseInt(number) >= 10 && Integer.parseInt(number) <= 50) {
					width = Integer.parseInt(number);
					break;
				}
				else {
					System.out.println("Выдолжны ввести целое число в диапазоне от 10 до 50.");
				}
			}

			world = World.createNewWorld(height, width);
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

	private static boolean containsInt(String str) {

		try {
			Integer.valueOf(str);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
}
