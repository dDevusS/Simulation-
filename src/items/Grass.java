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
		case 1: setMapSimbol("\u001B[33m🌱\u001B[0m"); break;
		case 2: setMapSimbol("\u001B[33m🌾\u001B[0m"); break;
		case 3: setMapSimbol("\u001B[33m🌻\u001B[0m"); break;
		}
	}

	//∭∬∫🌾🌱🌻🌵🥀🌹🌷🌼🧱
	public Grass(Integer x, Integer y) {
		this.coordinate=new Coordinate(x, y);
		Random random=new Random();
		int growth=random.nextInt(3);
		setFoodType(FoodType.GRASS);
		switch (growth) {
		case 0: setMapSimbol("\u001B[33m🌱\u001B[0m"); this.valueOfGrowth=1; this.timeOfLife=random.nextInt(1, 20); break;
		case 1: setMapSimbol("\u001B[33m🌾\u001B[0m"); this.valueOfGrowth=2; this.timeOfLife=random.nextInt(1, 20); break;
		case 2: setMapSimbol("\u001B[33m🌻\u001B[0m"); this.valueOfGrowth=3; this.timeOfLife=random.nextInt(1, 20); break;
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
			Coordinate cellForNewGrass=Pathfinder.getClosedEmptyRandomCell(this.coordinate, world);
			if (cellForNewGrass!=null)	{
				world.getMap().put(cellForNewGrass, getGrass(cellForNewGrass.x, cellForNewGrass.y));
			}
		}
		else {
			this.setValueOfGrowth(valueOfGrowth+1);
		}
	}
	
	public void isEaten(WorldMapNew world) {
		world.getMap().remove(coordinate);
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
