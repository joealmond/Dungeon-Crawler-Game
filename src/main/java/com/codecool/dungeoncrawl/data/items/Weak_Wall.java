package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Weak_Wall extends Item {
    private int DURABILITY = 2;

    public Weak_Wall(Cell cell) {
        super(cell, false);
    }

    @Override
    public String getTileName() {
        return "weak_wall";
    }

    @Override
    public void interactWithPlayer(Player player) {
        player.exhaustedStatus();
    }

    public void breakDown() {
        DURABILITY -= 1;

        if (DURABILITY == 1)animationService.setWallToCrumbling(cell);
        if (DURABILITY == 0) {
            cell.setItem(null);
            animationService.resetTileToFloor(cell);
        }
    }
}
