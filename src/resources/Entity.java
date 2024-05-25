package resources;

public abstract class Entity {

	protected Sprites sprite;
	protected Coordinate coordinate;

	public void remove(World world) {
		world.getMap().remove(coordinate);
	}

	public void setSprite(Sprites sprite) {
		this.sprite = sprite;
	}

	public Sprites getSprite() {
		return sprite;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(Integer x, Integer y) {
		this.coordinate = new Coordinate(x, y);
	}

	@Override
	public String toString() {
		return sprite.toString();
	}
}
