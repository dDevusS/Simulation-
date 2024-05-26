package resources;

public interface EntityFactory <T extends Entity> {

    T create();
}
