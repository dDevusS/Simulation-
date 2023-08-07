package items.food;

import resources.Coordinate;
import resources.Simulation;

public class Orange extends Food {

	public Orange(Integer x, Integer y) {
		setCoordinate(x, y);
		setMapSimbol("\u001B[31m🍊\u001B[0m");
		setQuantity(1);
		setTimeToDisappear(15);
		setType(FoodType.ORANGE);
	}
	
	public static Orange getApple(Coordinate emptyCell) {
		return new Orange(emptyCell.x, emptyCell.y);
	}
	
	@Override
	public void doAction(Simulation world) {
		if(this.timeToDisappear==0) {
			toDisappear(world);
		}
		else {
			this.timeToDisappear--;
		}
	}

}
