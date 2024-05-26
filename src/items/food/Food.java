package items.food;

import resources.*;

public abstract class Food extends Entity implements ActionCapable, CanBeEaten {

	protected int timeToDisappear;
	protected int quantity;
	protected int valueOfEnergy;

	public Food(Sprites sprites) {
		super(sprites);
	}

	public void decrease(World world, Coordinate coordinate) {
		quantity--;
		if (quantity == 0) {
			remove(world, coordinate);
		}
	}

	@Override
	public void doAction(World world, Coordinate coordinate) {
		timeToDisappear--;

		if (timeToDisappear == 0) {
			world.getMap().remove(coordinate);
		}
	}

	public int getValueOfEnergy() {
		return valueOfEnergy;
	}
}
