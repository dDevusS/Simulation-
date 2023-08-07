package items;
import resources.Entity;
import resources.Coordinate;
import resources.Simulation;

public class Rock extends Entity {
	
	//⩓
	public Rock(Integer x, Integer y) {
		this.coordinate=new Coordinate(x, y);
		setMapSimbol("🗻");
	}
	
	public void doAction(Simulation world) {}
	
	public static Rock getRock(Integer x, Integer y) {
		return new Rock(x, y);
	}
}
