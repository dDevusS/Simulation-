package items.plant;

import java.util.Random;
import items.food.Food.FoodType;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Sprites;
import resources.World;

public class Grass extends Plant {

	private int valueOfGrowth;
	private FoodType foodType;
	public static int quantityOfGrass = 0;

	public Grass(Integer x, Integer y) {
		this.coordinate = new Coordinate(x, y);
		Random random = new Random();
		int growth = random.nextInt(3);
		setFoodType(FoodType.GRASS);
		timeOfLife = random.nextInt(1, 20);

		switch (growth) {
		case 0:
			setSprite(Sprites.GRASS_STAGE_1);
			valueOfGrowth = 1;
			break;
		case 1:
			setSprite(Sprites.GRASS_STAGE_2);
			valueOfGrowth = 2;
			break;
		case 2:
			setSprite(Sprites.GRASS_STAGE_3);
			valueOfGrowth = 3;
			break;
		}
	}

	public void setValueOfGrowth(int valueOfGrowth) {
		this.valueOfGrowth = valueOfGrowth;

		switch (valueOfGrowth) {
		case 0:
			break;
		case 1:
			setSprite(Sprites.GRASS_STAGE_1);
			break;
		case 2:
			setSprite(Sprites.GRASS_STAGE_2);
			break;
		case 3:
			setSprite(Sprites.GRASS_STAGE_3);
			break;
		}
	}

	@Override
	public void doAction(World world) {
		timeOfLife++;

		if (timeOfLife % 7 == 0) {
			reproduce(world);
		}

		if (timeOfLife > 100) {
			timeOfLife = 1;
		}
	}

	private void reproduce(World world) {

		if (this.valueOfGrowth == 3) {
			Coordinate cellForNewGrass = Pathfinder.getClosedEmptyRandomCell(this.coordinate, world);

			if (cellForNewGrass != null) {
				world.getMap().put(cellForNewGrass, getGrass(cellForNewGrass.x(), cellForNewGrass.y()));
			}
		}
		else {
			setValueOfGrowth(valueOfGrowth + 1);
		}
	}

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public static Grass getGrass(Integer x, Integer y) {
		quantityOfGrass++;
		return new Grass(x, y);
	}

	public void isEaten(World world) {
		world.getMap().remove(coordinate);
		quantityOfGrass--;
	}

	public int getValueOfGrowth() {
		return valueOfGrowth;
	}
}
