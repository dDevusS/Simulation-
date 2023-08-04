package items;

import java.util.Random;

import resources.Coordinate;
import resources.Pathfinder;
import resources.WorldMapNew;

public class Three extends Plant {
	
	//∱ϴΟ
	public Three(Integer x, Integer y) {
		Random random=new Random();
		this.coordinate=new Coordinate(x, y);
		this.timeOfLife=random.nextInt(1, 20);
		setMapSimbol("\u001B[32m🌳\u001B[0m");
	}
	
	public static Three getThree(Integer x, Integer y) {
		return new Three(x, y);
	}
	
	public void reproduceApple(WorldMapNew world) {
		Coordinate newApple=Pathfinder.getClosedEmptyRandomCell(this.coordinate, world);
		if (newApple!=null)	{
			world.getMap().put(newApple, Apple.getApple(newApple));
		}
	}

	@Override
	public void doAction(WorldMapNew world) {
		this.timeOfLife++;
		if (this.timeOfLife%10==0) {
			reproduceApple(world);
		}
		if (this.timeOfLife>100) {
			this.timeOfLife=1;
		}
	}	
}
