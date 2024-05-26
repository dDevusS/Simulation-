package resources;

// ➖⬛⬜🟩🟨🟧🟥🟩🟦🟪🟫🔘🔴🟠⚫🟤🟣🔵⚪〰️
// 🐅🐆🐂🐃🐄🐖🐐🐕🐒🦍🐮🐷🐀🐇🦖🥓🥩🍗🍖🍊🍎
// ∭∬∫🌾🌱🌻🌵🥀🌹🌷🌼🧱

import creatures.herbivore.Cattle;
import creatures.predator.Tiger;
import items.Wall;
import items.Rock;
import items.food.Meat;
import items.food.Orange;
import items.plant.Grass;
import items.plant.Tree;

public enum Sprites {
    EMPTY_CELL(
            "🟫",
            () -> null),
    WALL(
            "\u001B[37m🧱",
            Wall::getWall),
    ROCK(
            "🗻",
            Rock::getRock),
    TREE(
            "\u001B[32m🌳\u001B[0m",
            Tree::getTree),
    GRASS_STAGE_1(
            "\u001B[33m🌱\u001B[0m",
            Grass::getGrass),
    GRASS_STAGE_2(
            "\u001B[33m🌾\u001B[0m",
            Grass::getGrass),
    GRASS_STAGE_3(
            "\u001B[33m🌻\u001B[0m",
            Grass::getGrass),
    MEAT(
            "\u001B[31m🥩\u001B[0m",
            Meat::getMeat),
    ORANGE(
            "\u001B[31m🍊\u001B[0m",
            Orange::getOrange),
    CATTLE(
            "🐂",
            Cattle::getCattle),
    TIGER(
            "\u001B[35m🐅\u001B[0m",
            Tiger::getTiger)
    ;

    private final String symbol;
    private final EntityFactory<? extends Entity> factory;

    Sprites(String symbol, EntityFactory<? extends Entity> factory) {
        this.symbol = symbol;
        this.factory = factory;
    }

    public Entity createEntity() {
        return factory.create();
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
