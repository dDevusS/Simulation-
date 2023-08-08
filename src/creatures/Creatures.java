package creatures;

import items.food.Meat;
import resources.*;

public abstract class Creatures extends Entity {

	protected int valueOfLife;
	protected int valueOfHunger;
	protected int age;
	protected CreaturesNames name;
	protected int speed;
	protected int previousIntention;
	protected int timeToReproduce;
	protected int attackPower;

	public void doMove(Coordinate closedCell, Simulation world) {

		if (closedCell != null) {
			world.getMap().put(closedCell, this);
			this.remove(world);
			world.getMap().get(closedCell).setCoordinate(closedCell.getX(), closedCell.getY());
		}
	}

	public void die(Simulation world) {
		this.remove(world);

		if (Herbivore.class.isAssignableFrom(this.getClass())) {
			Herbivore.quantityOfHerbivore--;
		}
		else {
			Predator.quantityOfPredator--;
		}
		world.getMap().put(coordinate, Meat.getMeat(coordinate));
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getTimeToReproduce() {
		return timeToReproduce;
	}

	public void setTimeToReproduce(int timeToReproduce) {
		this.timeToReproduce = timeToReproduce;
	}

	public int getPreviousIntention() {
		return previousIntention;
	}

	public void setPreviousIntention(int previousIntention) {
		this.previousIntention = previousIntention;
	}

	public void setValueOfLife(int volueOfLife) {
		this.valueOfLife = volueOfLife;
	}

	public void setValueOfHunger(int volueOfHunger) {
		this.valueOfHunger = volueOfHunger;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public CreaturesNames getName() {
		return name;
	}

	public void setName(CreaturesNames name) {
		this.name = name;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getValueOfLife() {
		return this.valueOfLife;
	}

	public int getValueOfHunger() {
		return this.valueOfHunger;
	}

	public int getAge() {
		return this.age;
	}

	public int getSpeed() {
		return this.speed;
	}

	public abstract void eating(Coordinate food, Simulation world);

	public abstract void reproduce(Simulation world);
}
