package items;


import java.util.Random;
import resources.Coordinate;
import resources.Pathfinder;
import resources.WorldMapNew;

public class Grass extends Plant {
	
	private int valueOfGrowth;
	
	//∭∬∫🌾🌱🌻🌵🥀🌹🌷🌼🧱
	public Grass(Integer x, Integer y) {
		this.coordinate=new Coordinate(x, y);
		Random random=new Random();
		int growth=random.nextInt(3);
		switch (growth) {
		case 0: setMapSimbol("🌱"); this.valueOfGrowth=1; this.timeOfLife=random.nextInt(1, 20); break;
		case 1: setMapSimbol("🌾"); this.valueOfGrowth=2; this.timeOfLife=random.nextInt(1, 20); break;
		case 2: setMapSimbol("🌻"); this.valueOfGrowth=3; this.timeOfLife=random.nextInt(1, 20); break;
		}
	}
	
	public static Grass getGrass(Integer x, Integer y) {
		return new Grass(x, y);
	}
	
	public void reproduce(WorldMapNew world) {
		if (this.valueOfGrowth==3) {
			Coordinate newGrass=Pathfinder.closedEmptyRandomCell(this.coordinate, world);
			if (newGrass!=null)	{
				world.getMap().put(newGrass, getGrass(newGrass.x, newGrass.y));
			}
		}
		else {
			this.valueOfGrowth++;
		}
	}
	
	public void isEaten(WorldMapNew world) {
		world.getMap().remove(this.coordinate);
	}

	@Override
	public void doAction(WorldMapNew world) {
		this.timeOfLife++;
		if (this.timeOfLife%10==0) {
			reproduce(world);
		}
		if (this.timeOfLife>100) {
			this.timeOfLife=1;
		}
	}
}
