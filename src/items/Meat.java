package items;

import resources.Coordinate;
import resources.Simulation;

public class Meat extends Food {

	public Meat(Integer x, Integer y) {
		setCoordinate(x, y);
		setMapSimbol("\u001B[31m🥩\u001B[0m");
		setQuantity(3);
		setTimeToDisappear(5);
		setType(FoodType.MEAT);
	}
	
	public static Meat getMeat(Coordinate emptyCell) {
		return new Meat(emptyCell.x, emptyCell.y);
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
