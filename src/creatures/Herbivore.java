package creatures;

import items.Grass;
import resources.Coordinate;
import resources.Intension;
import resources.Pathfinder;
import resources.Simulation;

public abstract class Herbivore extends Creatures {
	public static int quantityOfHerbivore=0;
	
	public void eating(Coordinate food, Simulation world) {
		boolean isHerb=Grass.class.isAssignableFrom(world.getMap().get(food).getClass());
		
		if (isHerb) {
			Grass grass=(Grass)world.getMap().get(food);
			grass.setValueOfGrowth(grass.getValueOfGrowth()-1);
			if (grass.getValueOfGrowth()==0) {
				grass.remove(world);
			}
			else {
				world.getMap().put(food, grass);
			}
		}
		//TODO:переделать часть кода.
		else {
			world.getMap().remove(food);
		}
		
		if(getVolueOfHunger()+30>100) {
			setVolueOfHunger(100);
		}
		else {
			setVolueOfHunger(getVolueOfHunger()+30);
		}
	}

	@Override
	public void doAction(Simulation world) {
		int counterTurn=speed;
		if(getAge()==0) {
			counterTurn=0;
		}
		
		if (volueOfHunger<0) {
			volueOfLife--;
		}
		
		if (volueOfLife<=0||age>=35) {
			this.die(world);
			return;
		}
		
		while (counterTurn>0) {
			switch (Intension.makeIntension(this, world)) {
			case WANT_EAT: Coordinate goal=Intension.findFood(this, world);
				if (goal==null) {
					if (Pathfinder.getClosedEmptyRandomCell(coordinate, world)!=null) {
						doMove(Pathfinder.getClosedEmptyRandomCell(coordinate, world), world);
					}
				}
				else if (Pathfinder.isClosedCell(goal, this, world)) {
					eating(goal, world);
				}
				else if (Pathfinder.findPath(goal, coordinate, world)!=null) {
					doMove(Pathfinder.findPath(goal, coordinate, world), world);
				}
				break;
			case WANT_REPRODUCE: reproduce(world);
				break;
			case WANT_STROLL: doMove(Pathfinder.getClosedEmptyRandomCell(coordinate, world), world); 
				break;
			}
			counterTurn--;
		}
		setVolueOfHunger(getVolueOfHunger()-5);
		if (volueOfHunger>50) {
			timeToReproduce--;
		}
		age++;
		if (volueOfHunger>50&volueOfLife<10) {
			volueOfLife++;
		}
	}
}

