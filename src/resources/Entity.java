package resources;

public abstract class Entity {

	protected Sprites sprite;

	public void remove(World world, Coordinate coordinate) {
		world.getMap().remove(coordinate);
	}

	public Entity(Sprites sprite) {
		this.sprite = sprite;
	}

	public void setSprite(Sprites sprite) {
		this.sprite = sprite;
	}

	public Sprites getSprite() {
		return sprite;
	}

	@Override
	public String toString() {
		return sprite.toString();
	}
}
