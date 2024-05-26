package items.plant;

import resources.*;

public abstract class Plant extends Entity implements ActionCapable {

	protected int timeOfLife;

	public Plant(Sprites sprites) {
		super(sprites);
	}

	public abstract void doAction(World world, Coordinate coordinate);
}
