package resources;

public abstract class Entity {

	protected String mapSymbol;
	protected Coordinate coordinate;

	public void remove(World world) {
		world.getMap().remove(coordinate);
	}

	public void setMapSymbol(String ch) {
		mapSymbol = ch;
	}

	public String getMapSymbol() {
		return mapSymbol;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(Integer x, Integer y) {
		this.coordinate = new Coordinate(x, y);
	}

	public abstract void doAction(World world);
}
