package items;

import resources.Coordinate;
import resources.WorldMapNew;

public class Apple extends Food {

	public Apple(Integer x, Integer y) {
		setCoordinate(x, y);
		setMapSimbol("\u001B[31m🍊\u001B[0m");
		setQuantity(1);
		setTimeToDisappear(15);
		setType(FoodType.APPLE);
	}
	
	public static Apple getApple(Coordinate emptyCell) {
		return new Apple(emptyCell.x, emptyCell.y);
	}
	
	@Override
	public void doAction(WorldMapNew world) {
		if(this.timeToDisappear==0) {
			toDisappear(world);
		}
		else {
			this.timeToDisappear--;
		}
	}

}
