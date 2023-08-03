package items;

import resources.Cell;
import resources.Coordinate;
import resources.WorldMapNew;

public class MapWall extends Cell {		
	
	protected static String mapSimbol="🧱";
	//▢
	public MapWall(Integer x, Integer y) {
		this.coordinate=new Coordinate(x, y);
		//setMapSimbol("🧱");
	}
	
	public void doAction(WorldMapNew world) {}
	
	public String getMapSimbol() {
		return mapSimbol;
	}
	
	public static MapWall getMapWall(Integer x, Integer y) {
		return new MapWall(x, y);
	}
}
