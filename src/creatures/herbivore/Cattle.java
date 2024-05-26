package creatures.herbivore;

import java.util.Random;

import creatures.Herbivore;
import resources.Sprites;

public class Cattle extends Herbivore {
	Random random = new Random();

	public Cattle() {
		super(Sprites.CATTLE);
		age = 0;
		speed = 2;
		timeToReproduce = random.nextInt(15, 21);
		valueOfHunger = 50;
		valueOfHealth = 20;
		attackPower = 2;
		limitOfAge = 35;
		limitOfBabies = 4;
		limitOfHealth = 20;
		limitTimeToReproduce = 25;
	}

	public static Cattle getCattle() {
		return new Cattle();
	}

}
