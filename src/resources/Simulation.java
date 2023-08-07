package resources;
import java.util.HashMap;
import java.util.Random;

import creatures.Cattle;
import creatures.Herbivore;
import creatures.Predator;
import creatures.Tiger;
import items.Grass;
import items.MapWall;
import items.Rock;
import items.Three;

public class Simulation {

		private HashMap<Coordinate, Entity> map;
		private int worldWidth;
		private int worldHeight;
		private int generation=0;
		
		
		public Simulation(int worldWidth, int worldHeight) {
			this.map=new HashMap<Coordinate, Entity>();
			this.worldWidth = worldWidth;
			this.worldHeight = worldHeight;
		}
		
		public static Simulation createWorld(Integer worldWidth, Integer worldHeight) {
			Simulation world=new Simulation(worldWidth,worldHeight);
			
			for(int ySize=0; ySize<=worldHeight; ySize++) {
				for(int xSize=0; xSize<=worldWidth; xSize++) {
					if (xSize == 0 || ySize == 0 || xSize == worldWidth || ySize == worldHeight) {
	                    world.getMap().put(new Coordinate(xSize, ySize), MapWall.getMapWall(xSize, ySize));
	                }
				}
			}
			return world;
		}
		
		public void createItems() {
			int numberRocks=this.worldHeight*this.worldWidth/17;
			int numberThrees=this.worldHeight*this.worldWidth/20;
			int numberGrass=this.worldHeight*this.worldWidth/4;
			//int numberRocks=0;
			//int numberThrees=0;
			//int numberGrass=1;
			Random random=new Random();
			
			while(numberRocks>0) {
				int x=random.nextInt(this.worldWidth);
				int y=random.nextInt(this.worldHeight);
				if(isEmpty(new Coordinate(x,y))) {
					this.map.put(new Coordinate(x,y), Rock.getRock(x, y));
					numberRocks--;
				}
			}
			
			while(numberThrees>0) {
				int x=random.nextInt(this.worldWidth);
				int y=random.nextInt(this.worldHeight);
				if(isEmpty(new Coordinate(x,y))) {
					this.map.put(new Coordinate(x,y), Three.getThree(x, y));
					numberThrees--;
				}
			}
			
			while(numberGrass>0) {
				int x=random.nextInt(this.worldWidth);
				int y=random.nextInt(this.worldHeight);
				if(isEmpty(new Coordinate(x,y))) {
					this.map.put(new Coordinate(x,y), Grass.getGrass(x, y));
					numberGrass--;
				}
			}
		}
		
		public void createCreatures() {
			int numberCattle=this.worldHeight*this.worldWidth/12;
			int numberWolf=this.worldHeight*this.worldWidth/25;
			Random random=new Random();
			
			while(numberCattle>0) {
				int x=random.nextInt(this.worldWidth);
				int y=random.nextInt(this.worldHeight);
				if(isEmpty(new Coordinate(x,y))) {
					this.map.put(new Coordinate(x,y), Cattle.getCattle(x, y));
					numberCattle--;
				}
			}
			
			while(numberWolf>0) {
				int x=random.nextInt(this.worldWidth);
				int y=random.nextInt(this.worldHeight);
				if(isEmpty(new Coordinate(x,y))) {
					this.map.put(new Coordinate(x,y), Tiger.getWolf(x, y));
					numberWolf--;
				}
			}
		}
		
		public int getWidth() {
			return this.worldWidth;
		}
		
		public int getHeight() {
			return this.worldHeight;
		}
		
		public HashMap<Coordinate, Entity> getMap() {
			return this.map;
		}
		
		public boolean isEmpty(Coordinate coordinate) {
			return !this.map.containsKey(coordinate);
		}

		public void doRendering(boolean isRun) {
			//➖⬛⬜🟩🟨🟧🟥🟩🟦🟪🟫🔘🔴🟠⚫🟤🟣🔵⚪〰️
			//🐅🐆🐂🐃🐄🐖🐐🐕🐒🦍🐮🐷🐀🐇🦖🥓🥩🍗🍖🍊🍎
			
			for(int ySize=0; ySize<=this.getHeight(); ySize++) {
				for(int xSize=0; xSize<=this.getWidth(); xSize++) {
					if (this.getMap().containsKey(Coordinate.doCoordinate(xSize, ySize))) {
						System.out.print(this.getMap().get(Coordinate.doCoordinate(xSize, ySize)).getMapSimbol());
					}
					else {System.out.print("🟫");}
				}
				switch (ySize) {
				case 0: System.out.print("Поколение "+generation);
					System.out.print(!isRun ? "  ПАУЗА" : " ");
					break;
				case 1: System.out.print("\u001B[35m🐅\u001B[0m - тигр  🐂 - бык  🟫 - пустая клетка");
					break;
				case 2: System.out.print("🗻 - камень  \u001B[32m🌳\u001B[0m - дерево  \u001B[31m🍊\u001B[0m - апельсин");
				break;	
				case 3: System.out.print("\u001B[33m🌱 🌾 🌻\u001B[0m - трава(от побега до цветка)  \u001B[31m🥩\u001B[0m - мясо");
				break;	
				case 4: System.out.print("Количесво травоядных: "+Herbivore.quantityOfHerbivore);
					break;
				case 5: System.out.print("Количесво хищников: "+Predator.quantityOfPredator);
					break;
				case 6: System.out.print(isRun ? "1 - поставить на паузу." : "1 - следующее поколение.");
				break;
				case 7: System.out.print(isRun ? "2 - выйти в главное меню." : "2 - возобновить симуляцию.");
				break;
				case 8: System.out.print(isRun ? "" : "3 - выйти в главное меню.");
				break;
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
		
		public void doTurn() {
			for (int y=0; y<this.getHeight(); y++) {
				for (int x=0; x<this.getWidth(); x++) {
					if (!this.isEmpty(Coordinate.doCoordinate(x, y))) {
						this.getMap().get(Coordinate.doCoordinate(x, y)).doAction(this);
					}
				}
			}
			generation++;
		}
}
