package creatures;

import items.plant.Grass;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Simulation;

public abstract class Herbivore extends Creatures {
	public static int quantityOfHerbivore = 0;

	public void eating(Coordinate food, Simulation world) {
		boolean isHerb = Grass.class.isAssignableFrom(world.getMap().get(food).getClass());

		if (isHerb) {
			Grass grass = (Grass) world.getMap().get(food);
			grass.setValueOfGrowth(grass.getValueOfGrowth() - 1);

			if (grass.getValueOfGrowth() == 0) {
				grass.isEaten(world);
			}
			else {
				world.getMap().put(food, grass);
			}
		}
		else {
			world.getMap().remove(food);
		}

		if (getValueOfHunger() + 20 > 100) {
			setValueOfHunger(100);
		}
		else {
			setValueOfHunger(getValueOfHunger() + 20);
		}
	}

	@Override
	public void doAction(Simulation world) {
		int counterTurn = speed;

		if (getAge() == 0) {
			counterTurn = 0;
		}

		if (valueOfHunger < 0) {
			valueOfLife--;
		}

		if (valueOfLife <= 0 || age >= 35) {
			this.die(world);
			return;
		}

		while (counterTurn > 0) {

			switch (Intension.makeIntension(this, world)) {
			case WANT_EAT:
				Coordinate goal = Intension.findFood(this, world);
				if (goal == null) {

					if (Pathfinder.getClosedEmptyRandomCell(coordinate, world) != null) {
						doMove(Pathfinder.getClosedEmptyRandomCell(coordinate, world), world);
					}
				}
				else if (Pathfinder.isClosedCell(goal, this, world)) {
					eating(goal, world);
				}
				else if (Pathfinder.findPath(goal, coordinate, world) != null) {
					doMove(Pathfinder.findPath(goal, coordinate, world), world);
				}
				break;
			case WANT_REPRODUCE:
				reproduce(world);
				break;
			case WANT_STROLL:
				doMove(Pathfinder.getClosedEmptyRandomCell(coordinate, world), world);
				break;
			}
			counterTurn--;
		}
		setValueOfHunger(getValueOfHunger() - 5);

		if (valueOfHunger > 50) {
			timeToReproduce--;
		}
		age++;

		if (valueOfHunger > 50 & valueOfLife < 10) {
			valueOfLife++;
		}
	}
}
