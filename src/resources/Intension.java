package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import creatures.Creatures;
import creatures.Herbivore;
import items.Apple;
import items.Grass;
import items.Meat;

public abstract class Intension {

	public static int makeIntension(Creatures creature, WorldMapNew world) {
		int intention;
		Random random=new Random();
		
		if (creature.getVolueOfHunger()+random.nextInt(0, 21)<50) {
			return intention=0;
		}
		else if(creature.getTimeToReproduce()<=0&&Pathfinder.closedEmptyRandomCell(creature.getCoordinate(), world)!=null) {
			return intention=1;
		}
		else {
			return intention=2;
		}
	}
	
	public static Coordinate findFood(Creatures creature, WorldMapNew world) {
		boolean isHerbivore=creature instanceof Herbivore;
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
		
		if (!listOfGoals.isEmpty()) {
			return listOfGoals.get(random.nextInt(0, listOfGoals.size()-1));
		}
		else { 
			return null;
		}
	}
	
	private static List<Coordinate> findMeat(Creatures creature, WorldMapNew world) {
		List<Coordinate> listOfGoals=new ArrayList<>();
		//TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding=10;
		
		while (listOfGoals.isEmpty()&&radiusFinding>0) {
			for (int modifierFinding=0,y=-1-modifierFinding; y<2+modifierFinding; y++, modifierFinding++) {
				for (int x=-1-modifierFinding; x<2+modifierFinding; x++) {
					if (!world.isEmpty(creature.getCoordinate().shiftCell(x, y))) {
						if (world.getMap().get(creature.getCoordinate().shiftCell(x, y)) instanceof Meat) {
							listOfGoals.add(creature.getCoordinate().shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--;
		}
		return listOfGoals;
	}
	
	private static List<Coordinate> findPrey(Creatures creature, WorldMapNew world) {
		List<Coordinate> listOfGoals=new ArrayList<>();
		//TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding=10;
		
		while (listOfGoals.isEmpty()&&radiusFinding>0) {
			for (int modifierFinding=0,y=-1-modifierFinding; y<2+modifierFinding; y++, modifierFinding++) {
				for (int x=-1-modifierFinding; x<2+modifierFinding; x++) {
					if (!world.isEmpty(creature.getCoordinate().shiftCell(x, y))) {
						if (world.getMap().get(creature.getCoordinate().shiftCell(x, y)) instanceof Herbivore) {
							listOfGoals.add(creature.getCoordinate().shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--;
		}
		return listOfGoals;
	}
	
	private static List<Coordinate> findHerb(Creatures creature, WorldMapNew world) {
		List<Coordinate> listOfGoals=new ArrayList<>();
		//TODO: радиус обзора. Надо решить будет ли он меняться для разных видов.
		int radiusFinding=10;
		
		while (listOfGoals.isEmpty()&&radiusFinding>0) {
			for (int modifierFinding=0,y=-1-modifierFinding; y<2+modifierFinding; y++, modifierFinding++) {
				for (int x=-1-modifierFinding; x<2+modifierFinding; x++) {
					if (!world.isEmpty(creature.getCoordinate().shiftCell(x, y))) {
						if (world.getMap().get(creature.getCoordinate().shiftCell(x, y)) instanceof Apple||world.getMap().get(creature.getCoordinate().shiftCell(x, y)) instanceof Grass) {
							listOfGoals.add(creature.getCoordinate().shiftCell(x, y));
						}
					}
				}
			}
			radiusFinding--;
		}
		return listOfGoals;
	}
}
