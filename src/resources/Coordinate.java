package resources;

import java.util.Objects;

//TODO: Разобраться с созданием координат здесь и в классе Cell.
public class Coordinate {
	
	public final int x;
	public final int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
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
	
	public static Coordinate doCoordinate(int x, int y) {
		Coordinate coordinate=new Coordinate(x, y);
		return coordinate;
	}
	
	public Coordinate shiftCell(int rateShiftX, int rateShiftY) {
		return new Coordinate(this.x+rateShiftX, this.y+rateShiftY);
	}
}
