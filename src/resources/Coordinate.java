package resources;

import java.util.Objects;

public class Coordinate {
	
	private final int x;
	private final int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Coordinate doCoordinate(int x, int y) {
		return new Coordinate(x, y);
	}
	
	public Coordinate shiftCell(int rateShiftX, int rateShiftY) {
		return new Coordinate(this.x+rateShiftX, this.y+rateShiftY);
	}

	public synchronized int getX() {
		return x;
	}

	public synchronized int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return x == other.x && y == other.y;
	}
}
