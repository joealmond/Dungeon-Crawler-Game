package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty", true),
    FLOOR("floor", true),
    WALL("wall", false),
    CAMPFIRE("campfire", false),
    DOOR("door", false),
    OPENED_DOOR("opened-door", true),
    KEY("key", true),
    SLASH("slash",false),
    CORPSE("corpse",true),
    GRAVE("grave",true),
    HURT_ACTOR("hurt-actor",false);

    private final String tileName;
    private final boolean isPassable;

    CellType(String tileName, boolean isPassable) {
        this.tileName = tileName;
        this.isPassable = isPassable;
    }

    public String getTileName() {
        return tileName;
    }
    public boolean isPassable() {
        return isPassable;
    }
}
