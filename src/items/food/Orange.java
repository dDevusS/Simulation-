package items.food;

import resources.Sprites;

public class Orange extends Food {

	public Orange() {
		super(Sprites.ORANGE);
		super.quantity = 1;
		super.timeToDisappear = 15;
		super.valueOfEnergy = 15;
	}

	public static Orange getOrange() {
		return new Orange();
	}

}
