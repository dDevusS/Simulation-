package resources;
import java.util.HashMap;
import java.util.Random;

import items.Grass;
import items.MapWall;
import items.Rock;
import items.Three;

public class WorldMapNew {

		private HashMap<Coordinate, Cell> map;
		private int worldWidth;
		private int worldHeight;
		
		public WorldMapNew(int worldWidth, int worldHeight) {
			this.map=new HashMap<Coordinate, Cell>();
			this.worldWidth = worldWidth;
			this.worldHeight = worldHeight;
		}
		
		public static WorldMapNew createWorld(Integer worldWidth, Integer worldHeight) {
			WorldMapNew world=new WorldMapNew(worldWidth,worldHeight);
			
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
			int numberGrass=this.worldHeight*this.worldWidth/8;
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
		
		public int getWidth() {
			return this.worldWidth;
		}
		
		public int getHeight() {
			return this.worldHeight;
		}
		
		public HashMap<Coordinate, Cell> getMap() {
			return this.map;
		}
		
		public boolean isEmpty(Coordinate coordinate) {
			return !this.map.containsKey(coordinate);
		}
}