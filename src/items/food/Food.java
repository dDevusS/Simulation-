package items.food;

import resources.Entity;
import resources.Simulation;

public abstract class Food extends Entity {
	
	protected int timeToDisappear;
	protected int quantity;
	protected FoodType type;
	
	public void toDisappear(Simulation world) {
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

	public FoodType getType() {
		return type;
	}

	public void setType(FoodType type) {
		this.type = type;
	}
}
