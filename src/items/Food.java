package items;

import resources.Cell;
import resources.WorldMapNew;

public abstract class Food extends Cell {
	
	protected int timeToDisappear;
	protected int quantity;
	protected String type;
	
	public void toDisappear(WorldMapNew world) {
		world.getMap().remove(this.coordinate);
	}
	
	public int getTimeToDisappear() {
		return timeToDisappear;
	}

	public void setTimeToDisappear(int timeToDisappear) {
		this.timeToDisappear = timeToDisappear;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract void doAction(WorldMapNew world);
}
