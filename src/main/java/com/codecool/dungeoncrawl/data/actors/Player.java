package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.Arrays;

public class Player extends Actor {
    private final Inventory inventory;

    public Player(Cell cell) {
        super(cell);
        this.inventory = new Inventory();
    }

    @Override
    public String getTileName() {
        return "player";
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        inventory.addItem(item);
    }

    public void pickUpItemIfPossible() {
        if (this.getCell().isPassable() && this.getCell().hasItem()) {
            inventory.addItem(this.getCell().getItem());
            this.getCell().setType(CellType.FLOOR);
        }
    }

    public void allowMovement(Cell nextCell) {
        if (nextCell.isPassable() && !nextCell.hasActor()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;

        }

        pickUpItemIfPossible();
    }

    public void checkIfHasKeyAtDoor() {
//        if () {
//            System.out.println("door found");
//
//        }
    }
}
