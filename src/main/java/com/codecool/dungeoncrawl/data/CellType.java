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
    HURT_ACTOR("hurt-actor",false),
    WEAK_WALL("weak-wall",false),
    CRUMBLING_WALL("crumbling-wall",false),
    HEALTH_POTION("health-potion", true),
    TORCH("torch", true),
    SWORD("sword", true),
    HEAL_ACTOR("heal-actor",false),
    FARMER("farmer", false),
    CHICKEN("chicken", true);

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
