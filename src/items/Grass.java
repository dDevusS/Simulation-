package items;


import java.util.Random;
import resources.Coordinate;
import resources.Pathfinder;
import resources.WorldMapNew;

public class Grass extends Plant {
	
	private int valueOfGrowth;
	private FoodType foodType;
	
	public int getValueOfGrowth() {
		return valueOfGrowth;
	}

	public void setValueOfGrowth(int valueOfGrowth) {
		this.valueOfGrowth = valueOfGrowth;
		switch (valueOfGrowth) {
		case 0: break;
		case 1: setMapSimbol("🌱"); break;
		case 2: setMapSimbol("🌾"); break;
		case 3: setMapSimbol("🌻"); break;
		}
	}

	//∭∬∫🌾🌱🌻🌵🥀🌹🌷🌼🧱
	public Grass(Integer x, Integer y) {
		this.coordinate=new Coordinate(x, y);
		Random random=new Random();
		int growth=random.nextInt(3);
		setFoodType(FoodType.GRASS);
		switch (growth) {
		case 0: setMapSimbol("🌱"); this.valueOfGrowth=1; this.timeOfLife=random.nextInt(1, 20); break;
		case 1: setMapSimbol("🌾"); this.valueOfGrowth=2; this.timeOfLife=random.nextInt(1, 20); break;
		case 2: setMapSimbol("🌻"); this.valueOfGrowth=3; this.timeOfLife=random.nextInt(1, 20); break;
		}
	}
	
	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public static Grass getGrass(Integer x, Integer y) {
		return new Grass(x, y);
	}
	
	public void reproduce(WorldMapNew world) {
		if (this.valueOfGrowth==3) {
			Coordinate newGrass=Pathfinder.getClosedEmptyRandomCell(this.coordinate, world);
			if (newGrass!=null)	{
				world.getMap().put(newGrass, getGrass(newGrass.x, newGrass.y));
			}
		}
		else {
			Grass grass=(Grass)world.getMap().get(coordinate);
			grass.setValueOfGrowth(valueOfGrowth+1);
			world.getMap().put(coordinate, grass);
		}
	}
	
	public void isEaten(WorldMapNew world) {
		world.getMap().remove(coordinate);
	}

	@Override
	public void doAction(WorldMapNew world) {
		this.timeOfLife++;
		if (this.timeOfLife%5==0) {
			reproduce(world);
		}
		if (this.timeOfLife>100) {
			this.timeOfLife=1;
		}
	}
}
