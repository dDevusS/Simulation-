import resources.Coordinate;
import resources.Simulation;

public class ActionWorld implements Runnable {
	
	public int timeOfWorld=1;
	private Simulation world;
	public boolean isRun=true;
	private Object lock=new Object();
	
	public ActionWorld (Integer worldWidth, Integer worldHeight) {
		this.world=Simulation.createWorld(worldWidth, worldHeight);
		world.createItems();
		world.createCreatures();
	}
	
	public int getTimeOfWorld() {
		return timeOfWorld;
	}

	public boolean isRun() {
		return isRun;
	}

	public void pauseSimulation() {
		isRun = false;
	}
	
	public void resumeSimulation() {
		isRun=true;
		synchronized (lock) {
			lock.notify();
		}
	}
	
	public void run() {
		world.doRendering(isRun);
		this.timeOfWorld++;
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (true) {
			synchronized (lock) {
				while (!isRun) {
					try {
						lock.wait();
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		
			world.doTurn();			
			world.doRendering(isRun);
			this.timeOfWorld++;
			
			try {
				Thread.sleep(2000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized Simulation getWorld() {
		return world;
	}
	
	
}
