package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;

public class Player extends Actor {
    private static final int MAX_HEALTH = 15;
    private final Inventory inventory;

    public Player(Cell cell) {
        super(cell, calculateStartHealth());
        this.inventory = new Inventory();
    }

    public static int calculateStartHealth() {
        return MAX_HEALTH;
    }

    @Override
    public String getTileName() {
        return "player";
    }

    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.getItems().remove(item);
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
        if (nextCell.hasItem()) {
            nextCell.getItem().interactWithPlayer(this);
        }
    }

    public void attack(int x, int y) {
        Cell cellToAttack = cell.getNeighbor(x, y);
        animationService.playSlashAnimation(cellToAttack);

        if (cellToAttack.hasActor()) {
            Actor enemy = cellToAttack.getActor();
            int enemyHealth = enemy.getCurrentHealth();

            enemy.setCurrentHealth(enemyHealth - 1);
        }
    }
}
