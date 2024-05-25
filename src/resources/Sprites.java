package resources;

// ➖⬛⬜🟩🟨🟧🟥🟩🟦🟪🟫🔘🔴🟠⚫🟤🟣🔵⚪〰️
// 🐅🐆🐂🐃🐄🐖🐐🐕🐒🦍🐮🐷🐀🐇🦖🥓🥩🍗🍖🍊🍎
// ∭∬∫🌾🌱🌻🌵🥀🌹🌷🌼🧱

public enum Sprites {
    EMPTY_CELL("🟫"),
    WALL("\u001B[37m🧱"),
    ROCK("🗻"),
    TREE("\u001B[32m🌳\u001B[0m"),
    GRASS_STAGE_1("\u001B[33m🌱\u001B[0m"),
    GRASS_STAGE_2("\u001B[33m🌾\u001B[0m"),
    GRASS_STAGE_3("\u001B[33m🌻\u001B[0m"),
    MEAT("\u001B[31m🥩\u001B[0m"),
    ORANGE("\u001B[31m🍊\u001B[0m"),
    CATTLE("🐂"),
    TIGER("\u001B[35m🐅\u001B[0m")
    ;

    private final String symbol;

    Sprites(String symbol) {
        this.symbol = symbol;
    };

    @Override
    public String toString() {
        return this.symbol;
    }
}
