package resources;

import java.util.Random;

import creatures.herbivore.Cattle;
import creatures.predator.Tiger;
import items.plant.Grass;

public abstract class Actions {
	private static Random random=new Random();

	public static void createNewCattle(Integer number, Simulation world) {
		while(number>0&&world.getMap().size()<(world.getHeight()+1)*(world.getWidth()+1)-5) {
			int x=random.nextInt(world.getWidth());
			int y=random.nextInt(world.getHeight());
			if(world.isEmpty(new Coordinate(x,y))) {
				world.getMap().put(new Coordinate(x,y), Cattle.getCattle(x, y));
				number--;
			}
		}
	}
	
	public static void createNewTiger(Integer number, Simulation world) {
		while(number>0&&world.getMap().size()<(world.getHeight()+1)*(world.getWidth()+1)-5) {
			int x=random.nextInt(world.getWidth());
			int y=random.nextInt(world.getHeight());
			if(world.isEmpty(new Coordinate(x,y))) {
				world.getMap().put(new Coordinate(x,y), Tiger.getWolf(x, y));
				number--;
			}
		}
	}
	
	public static void createNewGrass(Integer number, Simulation world) {
		while(number>0&&world.getMap().size()<(world.getHeight()+1)*(world.getWidth()+1)-5) {
			int x=random.nextInt(world.getWidth());
			int y=random.nextInt(world.getHeight());
			if(world.isEmpty(new Coordinate(x,y))) {
				world.getMap().put(new Coordinate(x,y), Grass.getGrass(x, y));
				number--;
			}
		}
	}
}
