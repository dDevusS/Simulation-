import resources.Coordinate;
import resources.WorldMapNew;
import resources.WorldMapRender;

public class ActionWorld implements Runnable {
	
	public int timeOfWorld=1;
	public WorldMapNew world;
	
	public ActionWorld (Integer worldWidth, Integer worldHeight) {
		this.world=WorldMapNew.createWorld(worldWidth, worldHeight);
		world.createItems();
	}
	
	public void run() {
		WorldMapRender.doVievWorld(world);
		this.timeOfWorld++;
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (true) {
			for (int y=0; y<world.getHeight(); y++) {
				for (int x=0; x<world.getWidth(); x++) {
					if (!world.isEmpty(Coordinate.doCoordinate(x, y))) {
						world.getMap().get(Coordinate.doCoordinate(x, y)).doAction(world);
					}
				}
			}
			
			WorldMapRender.doVievWorld(world);
			this.timeOfWorld++;
			try {
				Thread.sleep(3000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
