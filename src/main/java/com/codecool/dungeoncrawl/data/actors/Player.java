package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Player extends Actor {
    private static final int MAX_HEALTH = 15;
    private final Inventory inventory;

    public Player(Cell cell) {
        super(cell, calculateStartHealth());
        this.inventory = new Inventory();
    }

    public static int calculateStartHealth() {
        return Player.MAX_HEALTH;
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

    public void allowMovement(Cell nextCell) {
        if (nextCell.isPassable() && !nextCell.hasActor()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        interactWithPlayer(nextCell);
    }

    public void interactWithPlayer(Cell nextCell) {
        if (this.getCell().hasItem()) {
            this.getCell().getItem().interactWithPlayer(nextCell);
        }

        handleCampfireInteraction(nextCell);
        handleGoldenKeyInteraction(nextCell);
        handleDoorInteraction(nextCell);
    }

    private void handleCampfireInteraction(Cell nextCell) {
        if (checkIfNextCellHasGivenItem(nextCell, "campfire")) {
            if (this.getCurrentHealth() < Player.MAX_HEALTH) {
                this.currentHealth += 1;
            }
        }
    }

    private void handleGoldenKeyInteraction(Cell nextCell) {
        if (checkIfNextCellHasGivenItem(nextCell, "key")) {
            inventory.addItem(this.getCell().getItem());
            this.getCell().setType(CellType.FLOOR);
        }
    }

    private void handleDoorInteraction(Cell nextCell) {
        if (checkIfNextCellHasGivenItem(nextCell, "door") && checkItemInInventory("key")) {
            nextCell.getItem().getCell().setType(CellType.OPENED_DOOR);
            System.out.println("you shall pass!");
        }
    }

    private boolean checkIfNextCellHasGivenItem(Cell nextCell, String nameOfItem) {
        return nextCell.hasItem() && Objects.equals(nextCell.getItem().getTileName(), nameOfItem);
    }

    private boolean checkItemInInventory(String nameOfItem) {
        return inventory.getItems().stream().filter(item -> item.getTileName() == nameOfItem).collect(Collectors.toList()).size() == 1;
    }
}
