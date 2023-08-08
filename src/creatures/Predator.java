package creatures;

import java.util.Random;

import items.food.Meat;
import items.plant.Grass;
import resources.Coordinate;
import resources.Pathfinder;
import resources.Simulation;

public abstract class Predator extends Creatures {
	Random random = new Random();
	public static int quantityOfPredator = 0;

	@Override
	public void eating(Coordinate food, Simulation world) {
		boolean isMeat = Meat.class.isAssignableFrom(world.getMap().get(food).getClass());

		if (isMeat) {
			Meat meat = (Meat) world.getMap().get(food);
			meat.setQuantity(meat.getQuantity() - 1);
			if (meat.getQuantity() <= 0) {
				meat.remove(world);
			} else {
				world.getMap().put(food, meat);
			}

			if (getVolueOfHunger() + 20 > 100) {
				setVolueOfHunger(100);
			} else {
				setVolueOfHunger(getVolueOfHunger() + 20);
			}
		} else {
			Herbivore prey = (Herbivore) world.getMap().get(food);
			this.doAttack(prey, world);
		}
	}

	public void doAttack(Herbivore prey, Simulation world) {
		this.setVolueOfLife(volueOfLife - prey.getAttackPower() - random.nextInt(-3, 3));
		prey.setVolueOfLife(volueOfLife - this.getAttackPower() - random.nextInt(-1, 6));

		if (this.getVolueOfLife() <= 0) {
			this.die(world);
		} else {
			world.getMap().put(coordinate, this);
		}

		if (prey.getVolueOfLife() <= 0) {
			prey.die(world);
		} else {
			world.getMap().put(prey.getCoordinate(), prey);
		}
	}

	public void doAction(Simulation world) {
		int counterTurn = speed;
		if (getAge() == 0) {
			counterTurn = 0;
		}

		if (volueOfHunger < 0) {
			volueOfLife--;
		}

		if (volueOfLife <= 0 || age >= 50) {
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
				} else if (Pathfinder.isClosedCell(goal, this, world)) {
					eating(goal, world);
				} else if (Pathfinder.findPath(goal, coordinate, world) != null) {
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
		setVolueOfHunger(getVolueOfHunger() - 5);
		if (volueOfHunger > 50) {
			timeToReproduce--;
		}
		age++;
		if (volueOfHunger > 50 & volueOfLife < 10) {
			volueOfLife++;
		}
	}
}
