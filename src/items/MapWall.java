package items;

import resources.Entity;
import resources.Coordinate;
import resources.Simulation;

public class MapWall extends Entity {		
	
	protected static String mapSimbol="\u001B[37m🧱";
	//▢
	public MapWall(Integer x, Integer y) {
		this.coordinate=new Coordinate(x, y);
		//setMapSimbol("🧱");
	}
	
	public void doAction(Simulation world) {}
	
	public String getMapSimbol() {
		return mapSimbol;
	}
	
	public static MapWall getMapWall(Integer x, Integer y) {
		return new MapWall(x, y);
	}
}
