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

public class Player extends Actor {
    private final Inventory inventory;
    private AnimationService animationService;

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

    public void attack(int x, int y){
        Cell cellToAttack = cell.getNeighbor(x,y);
        animationService.playSlashAnimation(cellToAttack);
    }
    public void setAnimationService(AnimationService animationService){
        this.animationService =  animationService;
    }
}
