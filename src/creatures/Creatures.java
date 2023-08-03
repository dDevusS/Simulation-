package creatures;

import resources.*;

public abstract class Creatures extends Cell {

	protected int volueOfLife;
	protected int volueOfHunger;
	protected int age;
	protected String nameOfSpecies;
	protected int speed;
	protected int previousIntention;
	protected int timeToReproduce;

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
	
	public void setNameOfSpecies(String nameOfSpecies) {
		this.nameOfSpecies=nameOfSpecies;
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
	
	public String getNameOfSpecies() {
		return this.nameOfSpecies;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void doMove(Coordinate closedCell, WorldMapNew world) {
		world.getMap().put(closedCell, world.getMap().get(coordinate));
		world.getMap().remove(coordinate);
		world.getMap().get(closedCell).setCoordinate(closedCell.x, closedCell.y);		
	}
	
	public abstract void eating();
	
	public void die(WorldMapNew world) {
		world.getMap().remove(coordinate);
	}
	
	public abstract void reproduce();
	
	//TODO; решить где будет работать.
	public abstract int makeIntention();
	
	public Coordinate lookingFor(Cell obj, WorldMapNew world) {
		int minX=-1; int maxX=2;
		int minY=-1; int maxY=2;
		for (int sightSistance=10; sightSistance>=0; sightSistance--, minX--, maxX++, minY--, maxY++) {
			for (int y=minY; y<maxY; y++) {
				for (int x=minX; x<maxX; x++) {
					if (!world.isEmpty(this.coordinate.shiftCell(x, y))) {
						if (obj.getClass().isInstance(world.getMap().get(this.coordinate.shiftCell(x, y)))) {
							return this.coordinate.shiftCell(x, y);
						}
					}
				}
			}
		}
		return null;
	}
}
