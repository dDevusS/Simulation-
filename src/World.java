
import resources.Simulation;

public class World implements Runnable {

	public boolean isRun = true;
	private Simulation world;
	private Object lock = new Object();

	public World(Integer worldWidth, Integer worldHeight) {
		this.world = Simulation.createWorld(worldWidth, worldHeight);
		world.createItems();
		world.createCreatures();
	}

	public void run() {
		world.doRendering(isRun);

		try {
			Thread.sleep(2000);
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

			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isRun() {
		return isRun;
	}

	public void pause() {
		isRun = false;
	}

	public void resumeSimulation() {
		isRun = true;

		synchronized (lock) {
			lock.notify();
		}
	}

	public synchronized Simulation getWorld() {
		return world;
	}

}
