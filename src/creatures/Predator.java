package creatures;

import java.util.Random;

import items.Grass;
import items.Meat;
import resources.Coordinate;
import resources.Intension;
import resources.Pathfinder;
import resources.WorldMapNew;

public abstract class Predator extends Creatures {
	Random random=new Random();
	
	@Override
	public void eating(Coordinate food, WorldMapNew world) {
		boolean isMeat=Meat.class.isAssignableFrom(world.getMap().get(food).getClass());
		
		if (isMeat) {
			Meat meat=(Meat)world.getMap().get(food);
			meat.setQuantity(meat.getQuantity()-1);
			if (meat.getQuantity()==0) {
				world.getMap().remove(meat.getCoordinate());
			}
			else {
				world.getMap().put(food, meat);
			}
			
			if(getVolueOfHunger()+20>100) {
				setVolueOfHunger(100);
			}
			else {
				setVolueOfHunger(getVolueOfHunger()+20);
			}
		}
		else {
			Herbivore prey=(Herbivore)world.getMap().get(food);
			Predator predator=(Predator)world.getMap().get(coordinate);
			predator.doAttack(prey, world);
		}
	}

	public void doAttack(Herbivore prey, WorldMapNew world) {
		Predator predator=(Predator)world.getMap().get(coordinate);
		predator.setVolueOfLife(volueOfLife-prey.getAttackPower()-random.nextInt(-3, 3));
		prey.setVolueOfLife(volueOfLife-predator.getAttackPower()-random.nextInt(-1, 6));
		
		if (predator.getVolueOfLife()<=0) {
			predator.die(world);
		}
		else {
			world.getMap().put(coordinate, predator);
		}
		
		if (prey.getVolueOfLife()<=0) {
			prey.die(world);
		}
		else {
			world.getMap().put(prey.getCoordinate(), prey);
		}
	}
	
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
