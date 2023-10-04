package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.AnimationService;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.Objects;

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
//        if (this.getCell().hasItem()) {
//            this.getCell().getItem().interactWithPlayer(nextCell, currentHealth);
//        }
        handleCampfireInteraction(nextCell);
        handleGoldenKeyInteraction(nextCell);
    }

    private void handleCampfireInteraction(Cell nextCell) {

    }

    private void handleGoldenKeyInteraction(Cell nextCell) {
        if (checkIfNextCellHasGivenItem(nextCell, "key")) {
            inventory.addItem(this.getCell().getItem());
            this.getCell().setType(CellType.FLOOR);
        }
    }

    private boolean checkIfNextCellHasGivenItem(Cell nextCell, String nameOfItem) {
        return nextCell.hasItem() && Objects.equals(nextCell.getItem().getTileName(), nameOfItem);
    }

    public void attack(int x, int y){
        Cell cellToAttack = cell.getNeighbor(x,y);
        animationService.playSlashAnimation(cellToAttack);

        if(cellToAttack.hasActor()){
            Actor enemy = cellToAttack.getActor();
            int enemyHealth = enemy.getCurrentHealth();

            enemy.setCurrentHealth(enemyHealth - 1);
            System.out.println(enemy.getCurrentHealth());
        }
    }

}
