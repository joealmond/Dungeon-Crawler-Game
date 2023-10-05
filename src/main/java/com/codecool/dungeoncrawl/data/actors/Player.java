package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.data.items.Weak_Wall;

public class Player extends Actor {
    private static final int MAX_HEALTH = 15;
    private String activeWeapon = "Cudgel";
    private final Inventory inventory;

    public Player(Cell cell) {
        super(cell, calculateStartHealth(), 1);
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

    public String getActiveWeapon() {
        return activeWeapon;
    }

    public void setActiveWeapon(String activeWeapon) {
        this.activeWeapon = activeWeapon;
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

            enemy.setCurrentHealth(enemyHealth - DAMAGE);
        }

        if(cellToAttack.getItem() instanceof Weak_Wall){
            Weak_Wall wall = (Weak_Wall) cellToAttack.getItem();
            wall.breakDown();
        }
    }
    public void exhaustedStatus(){
        System.out.println("Player bumped into wall");
    }
}
