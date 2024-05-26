package resources;

public interface CanBeEaten {

    int getValueOfEnergy();
    void decrease(World world, Coordinate coordinate);
}
