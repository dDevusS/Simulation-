package resources;

public record Coordinate(int x, int y) {

	public static Coordinate createCoordinates(int x, int y) {
		return new Coordinate(x, y);
	}

	public Coordinate shiftCell(int rateShiftX, int rateShiftY) {
		return new Coordinate(this.x + rateShiftX, this.y + rateShiftY);
	}

}
