package resources;

// TODO: переписать абстрактный класс
// mapSimbol должен быть абстрактен

public abstract class Entity {
	
	protected String mapSimbol;
	protected Coordinate coordinate;
	
	public void remove(Simulation world) {
		world.getMap().remove(coordinate);
	}
	
	public void setMapSimbol(String ch) {
		mapSimbol=ch;
	}
	
	public String getMapSimbol() {
		return mapSimbol;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(Integer x, Integer y) {
		Coordinate coordinate=new Coordinate(x, y);
		this.coordinate = coordinate;
	}
	
	public abstract void doAction(Simulation world);
}