package items;

import resources.Cell;
import resources.WorldMapNew;

public abstract class Plant extends Cell {

	protected int valueOfGrowth;
	protected int timeOfLife;
	
	public int getValueOfGrowth() {
		return valueOfGrowth;
	}
	
	public void setValueOfGrowth(int valueOfGrowth) {
		this.valueOfGrowth = valueOfGrowth;
	}
	
	public int getTimeOfLife() {
		return timeOfLife;
	}
	
	public void setTimeOfLife(int timeOfLife) {
		this.timeOfLife = timeOfLife;
	}

	public abstract void doAction(WorldMapNew world);
}
