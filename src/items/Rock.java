package items;

import resources.Entity;
import resources.Coordinate;
import resources.Sprites;
import resources.World;

public class Rock extends Entity {

	public Rock() {
		setSprite(Sprites.ROCK);
	}

	public static Rock getRock() {
		return new Rock();
	}
}
