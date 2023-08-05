package creatures;

import items.Meat;
import resources.*;

public abstract class Creatures extends Cell {

	protected int volueOfLife;
	protected int volueOfHunger;
	protected int age;
	protected CreaturesNames name;
	protected int speed;
	protected int previousIntention;
	protected int timeToReproduce;
	protected int attackPower;

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

	public void setVolueOfLife(int volueOfLife) {
		this.volueOfLife=volueOfLife;
	}
	
	public void setVolueOfHunger(int volueOfHunger) {
		this.volueOfHunger=volueOfHunger;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public CreaturesNames getName() {
		return name;
	}

	public void setName(CreaturesNames name) {
		this.name = name;
	}

	public void setSpeed(int speed) {
		this.speed=speed;
	}
	
	public int getVolueOfLife() {
		return this.volueOfLife;
	}
	
	public int getVolueOfHunger() {
		return this.volueOfHunger;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void doMove(Coordinate closedCell, WorldMapNew world) {
		if(closedCell!=null) {
			world.getMap().put(closedCell, this);
			this.remove(world);
			world.getMap().get(closedCell).setCoordinate(closedCell.x, closedCell.y);
		}
	}
	
	public abstract void eating(Coordinate food, WorldMapNew world);
	
	public void die(WorldMapNew world) {
		this.remove(world);
		world.getMap().put(coordinate, Meat.getMeat(coordinate));
	}
	
	public abstract void reproduce(WorldMapNew world);
}
