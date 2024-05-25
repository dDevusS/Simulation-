package items;

import resources.Entity;
import resources.Sprites;

public class MapWall extends Entity {

	public MapWall() {
		setSprite(Sprites.WALL);
	}

	public static MapWall getMapWall() {
		return new MapWall();
	}
}
