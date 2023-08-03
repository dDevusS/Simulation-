package items;
import resources.Cell;
import resources.Coordinate;
import resources.WorldMapNew;

public class Rock extends Cell {
	
	//⩓
	public Rock(Integer x, Integer y) {
		this.coordinate=new Coordinate(x, y);
		setMapSimbol("🗻");
	}
	
	public void doAction(WorldMapNew world) {}
	
	public static Rock getRock(Integer x, Integer y) {
		return new Rock(x, y);
	}
}
