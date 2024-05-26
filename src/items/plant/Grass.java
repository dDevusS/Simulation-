package items.plant;

import resources.*;

import static utils.Utils.RANDOM;

public class Grass extends Plant implements CanBeEaten {

    private int valueOfGrowth;
    private static final int valueOfEnergy = 10;
    private static final int REPRODUCE_TIME = 8;
    private static final int SPROUT_INDEX = 1;

    public Grass() {
        super(Sprites.GRASS_STAGE_1);
        valueOfGrowth = RANDOM.nextInt(1, 4);
        timeOfLife = RANDOM.nextInt(1, 5);
        changeSprite(valueOfGrowth);
    }

    public Grass(int SPROUT_INDEX) {
        super(Sprites.GRASS_STAGE_1);
        valueOfGrowth = SPROUT_INDEX;
        timeOfLife = SPROUT_INDEX;
        setSprite(Sprites.GRASS_STAGE_1);
    }

    private void grow() {
        valueOfGrowth++;
        changeSprite(valueOfGrowth);
    }

    @Override
    public void doAction(World world, Coordinate coordinate) {
        timeOfLife++;

        if (timeOfLife % REPRODUCE_TIME == 0) {
            reproduce(world, coordinate);
            timeOfLife = 1;
        }
    }

    private void reproduce(World world, Coordinate coordinate) {

        if (this.valueOfGrowth == 3) {
            Coordinate cellForNewGrass = Pathfinder.getClosedEmptyRandomCell(coordinate, world);

            if (cellForNewGrass != null) {
                world.getMap().put(cellForNewGrass, new Grass(SPROUT_INDEX));
            }
        }
        else {
            grow();
        }
    }

    public static Grass getGrass() {
        return new Grass();
    }

    public void decrease(World world, Coordinate coordinate) {
        if (valueOfGrowth == 1) {
            world.getMap().remove(coordinate);
        }
        valueOfGrowth--;
        changeSprite(valueOfGrowth);
    }

    private void changeSprite(int valueOfGrowth) {
        switch (valueOfGrowth) {
            case 1 -> setSprite(Sprites.GRASS_STAGE_1);
            case 2 -> setSprite(Sprites.GRASS_STAGE_2);
            case 3 -> setSprite(Sprites.GRASS_STAGE_3);
        }
    }

    public int getValueOfEnergy() {
        return valueOfEnergy;
    }
}
