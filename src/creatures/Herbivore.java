package creatures;

import items.Grass;
import resources.Coordinate;
import resources.Intension;
import resources.Pathfinder;
import resources.WorldMapNew;

public abstract class Herbivore extends Creatures {
	
	public void eating(Coordinate food, WorldMapNew world) {
		boolean isHerb=Grass.class.isAssignableFrom(world.getMap().get(food).getClass());
		
		if (isHerb) {
			Grass grass=(Grass)world.getMap().get(food);
			grass.setValueOfGrowth(grass.getValueOfGrowth()-1);
			if (grass.getValueOfGrowth()==0) {
				world.getMap().remove(grass.getCoordinate());
			}
			else {
				world.getMap().put(food, grass);
			}
		}
		else {
			world.getMap().remove(food);
		}
		
		if(getVolueOfHunger()+20>100) {
			setVolueOfHunger(100);
		}
		else {
			setVolueOfHunger(getVolueOfHunger()+20);
		}
	}

	@Override
	public void doAction(WorldMapNew world) {
		int counterTurn=speed;
		if(getAge()==0) {
			counterTurn=0;
		}
		Creatures creature=(Creatures)world.getMap().get(coordinate);
		
		if (volueOfHunger<0) {
			volueOfLife--;
		}
		
		if (volueOfLife<=0||age>=35) {
			creature.die(world);
			return;
		}
		
		while (counterTurn>0) {
			switch (Intension.makeIntension(creature, world)) {
			case WANT_EAT: Coordinate goal=Intension.findFood(creature, world);
				if (goal==null) {
					if (Pathfinder.getClosedEmptyRandomCell(coordinate, world)!=null) {
						doMove(Pathfinder.getClosedEmptyRandomCell(coordinate, world), world);
					}
				}
				else if (Pathfinder.isClosedCell(goal, creature, world)) {
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
		timeToReproduce--;
		age++;
	}
}

