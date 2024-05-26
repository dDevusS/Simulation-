package items.food;

import resources.Sprites;

public class Meat extends Food {

	public Meat() {
		super(Sprites.MEAT);
		super.quantity = 3;
		super.timeToDisappear = 5;
		super.valueOfEnergy = 20;
	}

	public static Meat getMeat() {
		return new Meat();
	}

}
