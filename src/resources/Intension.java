package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import creatures.Creatures;
import creatures.Herbivore;
import items.Orange;
import items.Grass;
import items.Meat;

public abstract class Intension {
	
	public static TypeIntension makeIntension(Creatures creature, Simulation world) {
		TypeIntension intention;
		Random random=new Random();
		
		if (creature.getVolueOfHunger()+random.nextInt(0, 21)<50) {
			return intention=TypeIntension.WANT_EAT;
		}
		else if(creature.getTimeToReproduce()<=0&&Pathfinder.getClosedEmptyRandomCell(creature.getCoordinate(), world)!=null) {
			return intention=TypeIntension.WANT_REPRODUCE;
		}
		else {
			return intention=TypeIntension.WANT_STROLL;
		}
	}
	
	public static Coordinate findFood(Creatures creature, Simulation world) {
		//Разобраться с этим!!! Существо не может быть наследником травоядного!!!!!
		boolean isHerbivore=Herbivore.class.isAssignableFrom(creature.getClass());
		List<Coordinate> listOfGoals;
		Random random=new Random();
		
		if (isHerbivore) {
			listOfGoals=findHerb(creature, world);
		}
		else {
			listOfGoals=findMeat(creature, world);
			if(listOfGoals.isEmpty()) {
				listOfGoals=findPrey(creature, world);
			}
		}
		
		if (listOfGoals.size()>=1) {
			return listOfGoals.get(random.nextInt(0, listOfGoals.size()));
		}
		else {
			return null;
		}
	}
	
	private static List<Coordinate> findMeat(Creatures creature, Simulation world) {
		List<Coordinate> listOfGoals=new ArrayList<>();
		//TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding=3;
		int modifierFinding=0;
		
		while (listOfGoals.isEmpty()&&radiusFinding>0) {
			for (int y=-1-modifierFinding; y<2+modifierFinding; y++) {
				for (int x=-1-modifierFinding; x<2+modifierFinding; x++) {
					if (!world.isEmpty(creature.getCoordinate().shiftCell(x, y))) {
						if (world.getMap().get(creature.getCoordinate().shiftCell(x, y)) instanceof Meat) {
							listOfGoals.add(creature.getCoordinate().shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--; modifierFinding++;
		}
		return listOfGoals;
	}
	
	private static List<Coordinate> findPrey(Creatures creature, Simulation world) {
		List<Coordinate> listOfGoals=new ArrayList<>();
		//TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding=10;
		int modifierFinding=0;
		
		while (listOfGoals.isEmpty()&&radiusFinding>0) {
			for (int y=-1-modifierFinding; y<2+modifierFinding; y++) {
				for (int x=-1-modifierFinding; x<2+modifierFinding; x++) {
					if (!world.isEmpty(creature.getCoordinate().shiftCell(x, y))) {
						if (world.getMap().get(creature.getCoordinate().shiftCell(x, y)) instanceof Herbivore) {
							listOfGoals.add(creature.getCoordinate().shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--; modifierFinding++;
		}
		return listOfGoals;
	}
	
	private static List<Coordinate> findHerb(Creatures creature, Simulation world) {
		List<Coordinate> listOfGoals=new ArrayList<>();
		//TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding=10;
		int modifierFinding=0;
		
		while (listOfGoals.isEmpty()&&radiusFinding>0) {
			for (int y=-1-modifierFinding; y<2+modifierFinding; y++) {
				for (int x=-1-modifierFinding; x<2+modifierFinding; x++) {
					if (!world.isEmpty(creature.getCoordinate().shiftCell(x, y))) {
						if (Orange.class.isAssignableFrom(world.getMap().get(creature.getCoordinate().shiftCell(x, y)).getClass())||Grass.class.isAssignableFrom(world.getMap().get(creature.getCoordinate().shiftCell(x, y)).getClass())) {
							listOfGoals.add(creature.getCoordinate().shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--; modifierFinding++;
		}
		return listOfGoals;
	}
}
