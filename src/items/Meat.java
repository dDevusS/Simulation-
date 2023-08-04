package items;

import resources.Coordinate;
import resources.WorldMapNew;

public class Meat extends Food {

	public Meat(Integer x, Integer y) {
		setCoordinate(x, y);
		setMapSimbol("🥩");
		setQuantity(3);
		setTimeToDisappear(5);
		setType(FoodType.MEAT);
	}
	
	public static Meat getMeat(Coordinate emptyCell) {
		return new Meat(emptyCell.x, emptyCell.y);
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
