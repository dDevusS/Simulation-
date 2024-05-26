package creatures;

import items.food.Meat;
import resources.*;

import static utils.Utils.RANDOM;

public abstract class Creatures extends Entity implements ActionCapable {

    protected int valueOfHealth;
    protected int limitOfHealth;
    protected int valueOfHunger;
    protected int age;
    protected int limitOfAge;
    protected int speed;
    protected int attackPower;
    protected int timeToReproduce;
    protected int limitOfBabies;
    protected int limitTimeToReproduce;

    protected final int REPRODUCE_PRICE = 40;
    protected final int LIMIT_OF_HUNGER = 100;

    public Creatures(Sprites sprites) {
        super(sprites);
    }

    @Override
    public void doAction(World world, Coordinate coordinate) {
        int counterTurn = speed;

        if (age == 0) {
            counterTurn = 0;
        }

        if (valueOfHealth < limitOfHealth && valueOfHunger >= 50) {
            valueOfHealth++;
            valueOfHunger -=10;
        }
        else if (valueOfHunger >= 50) {
            timeToReproduce--;
        }
        else {
            valueOfHunger -=5;
        }

        if (valueOfHunger < 0) {
            valueOfHealth--;
        }

        if (valueOfHealth <= 0 || age >= limitOfAge) {
            this.die(world, coordinate);
            return;
        }

        age++;

        while (counterTurn > 0) {

            switch (Intention.makeIntention(world, coordinate)) {
                case WANT_EAT -> {
                    Coordinate foodLocation = Intention.findFood(world, coordinate);
                    Creatures creature = (Creatures) world.getMap().get(coordinate);

                    if (foodLocation == null && creature instanceof Herbivore) {
                        coordinate =
                        doMove(Pathfinder.getClosedEmptyRandomCell(coordinate, world), world, coordinate);
                    }
                    else if (foodLocation == null && creature instanceof Predator predator) {
                        Coordinate preyLocation = predator.findPrey(world, coordinate);

                        if (preyLocation != null && Pathfinder.isClosedCell(preyLocation, coordinate)) {
                            predator.doAttack(preyLocation, world, coordinate);
                            if (!(world.getMap().get(coordinate) instanceof Predator)) {
                                counterTurn = 0;
                            }
                        }
                        else {
                            coordinate =
                            doMove(Pathfinder.findPath(preyLocation, world, coordinate), world, coordinate);
                        }
                    }
                    else if (Pathfinder.isClosedCell(foodLocation, coordinate)) {
                        eat(world, foodLocation);
                    }
                    else {
                        coordinate =
                        doMove(Pathfinder.findPath(foodLocation, world, coordinate)
                                , world
                                , coordinate);
                    }

                    if (coordinate == null) {
                        counterTurn = 0;
                    }
                }
                case WANT_REPRODUCE -> reproduce(world, coordinate);
                case WANT_STROLL -> {
                    coordinate = doMove(Pathfinder.getClosedEmptyRandomCell(coordinate, world)
                        , world
                        , coordinate);

                    if (coordinate == null) {
                        counterTurn = 0;
                    }
                }
            }
            counterTurn--;
        }
    }

    public Coordinate doMove(Coordinate closedCell, World world, Coordinate currentCell) {

        if (closedCell != null) {
            world.getMap().put(closedCell, this);
            this.remove(world, currentCell);
        }

        return closedCell;
    }

    public void die(World world, Coordinate coordinate) {
        this.remove(world, coordinate);
        UserActions.decreaseQuantityOfCreatures(world, this.sprite);
        world.getMap().put(coordinate, Meat.getMeat());
    }

    public void reproduce(World world, Coordinate coordinate) {
        for (int numberOfBabies = RANDOM.nextInt(1, limitOfBabies); numberOfBabies > 0; numberOfBabies--) {
            Coordinate cellForBabies = Pathfinder.getClosedEmptyRandomCell(coordinate, world);

            if (cellForBabies != null) {
                world.getMap().put(cellForBabies, getSprite().createEntity());
                UserActions.increaseQuantityOfCreatures(world, getSprite());
            }
        }
        timeToReproduce = RANDOM.nextInt(limitTimeToReproduce - 3, limitTimeToReproduce);
        valueOfHunger -= REPRODUCE_PRICE;
    }

    public void eat(World world, Coordinate foodLocation) {
        Entity someFood = world.getMap().get(foodLocation);

        if (someFood instanceof CanBeEaten) {
            valueOfHunger = Math.min(valueOfHunger + ((CanBeEaten) someFood).getValueOfEnergy(), LIMIT_OF_HUNGER);
            ((CanBeEaten) someFood).decrease(world, foodLocation);
        }
    }

}
