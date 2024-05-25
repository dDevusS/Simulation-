package resources;

public record Coordinate(int x, int y) {

	public static Coordinate createCoordinates(int x, int y) {
		return new Coordinate(x, y);
	}

	public Coordinate shiftCell(int rateShiftX, int rateShiftY) {
		return new Coordinate(this.x + rateShiftX, this.y + rateShiftY);
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
