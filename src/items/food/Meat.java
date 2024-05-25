package items.food;

import resources.Coordinate;
import resources.Sprites;
import resources.World;

public class Meat extends Food {

	public Meat(Integer x, Integer y) {
		setCoordinate(x, y);
		setSprite(Sprites.MEAT);
		setQuantity(3);
		setTimeToDisappear(5);
		setType(FoodType.MEAT);
	}

	public static Meat getMeat(Coordinate emptyCell) {
		return new Meat(emptyCell.x(), emptyCell.y());
	}

	@Override
	public void doAction(World world) {

		if (timeToDisappear == 0) {
			toDisappear(world);
		}
		else {
			timeToDisappear--;
		}
	}
}
