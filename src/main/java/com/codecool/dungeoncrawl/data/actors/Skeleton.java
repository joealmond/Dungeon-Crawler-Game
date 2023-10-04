package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Skeleton extends Actor {
    private static final int MAX_HEALTH = 10;

    public Skeleton(Cell cell) {
        super(cell, calculateStartHealth());
    }

    public static int calculateStartHealth() {
        return Skeleton.MAX_HEALTH;
    }

    public void allowMovement(Cell nextCell) {
        if (nextCell.isPassable() && !nextCell.hasActor()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
