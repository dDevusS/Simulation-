package items.food;

import resources.Coordinate;
import resources.Sprites;
import resources.World;

public class Orange extends Food {

	public Orange(Integer x, Integer y) {
		setCoordinate(x, y);
		setSprite(Sprites.ORANGE);
		setQuantity(1);
		setTimeToDisappear(15);
		setType(FoodType.ORANGE);
	}

	public static Orange getApple(Coordinate emptyCell) {
		return new Orange(emptyCell.x(), emptyCell.y());
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
