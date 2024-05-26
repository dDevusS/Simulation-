package creatures.predator;

import java.util.Random;

import creatures.Predator;
import resources.Sprites;

public class Tiger extends Predator {

	public Tiger() {
		super(Sprites.TIGER);
		Random random = new Random();
		age = 0;
		speed = 3;
		timeToReproduce = random.nextInt(10, 16);
		valueOfHunger = 50;
		valueOfHealth = 15;
		attackPower = 10;
		limitOfAge = 30;
		limitOfBabies = 2;
		limitOfHealth = 15;
		limitTimeToReproduce = 15;
	}

	public static Tiger getTiger() {
		return new Tiger();
	}

}
