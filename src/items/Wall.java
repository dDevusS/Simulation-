package items;

import resources.Entity;
import resources.Sprites;

public class Wall extends Entity {

	public Wall() {
		super(Sprites.WALL);
		setSprite(Sprites.WALL);
	}

	public static Wall getWall() {
		return new Wall();
	}
}
