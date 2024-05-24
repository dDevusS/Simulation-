package items;

import resources.Entity;
import resources.Coordinate;
import resources.World;
import resources.WorldRender;

public class Rock extends Entity {

	public Rock(Integer x, Integer y) {
		this.coordinate = new Coordinate(x, y);
		setMapSymbol("🗻");
	}

	public void doAction(World world) {
	}

	public static Rock getRock(Integer x, Integer y) {
		return new Rock(x, y);
	}
}
