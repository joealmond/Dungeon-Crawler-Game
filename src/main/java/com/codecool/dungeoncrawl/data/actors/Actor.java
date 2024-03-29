package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.logic.AnimationService;

public abstract class Actor implements Drawable {
    protected int DAMAGE;
    protected Cell cell;
    protected int currentHealth;
    protected AnimationService animationService;

    public Actor(Cell cell, int currentHealth, int DAMAGE) {
        this.cell = cell;
        this.cell.setActor(this);
        this.currentHealth = currentHealth;
        this.DAMAGE = DAMAGE;
    }

    public abstract void allowMovement(Cell nextCell);

    public void move(int dx, int dy) {
        if(currentHealth > 0){
            Cell nextCell = cell.getNeighbor(dx, dy);
            allowMovement(nextCell);
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        if (this.currentHealth > currentHealth) {
            animationService.playActorGetHurtAnimation(cell);
        } else {
            animationService.playActorHealAnimation(cell);
        }

        this.currentHealth = currentHealth;

        if (currentHealth <= 0) {
            cell.setActor(null);
            animationService.playDeathAnimation(cell);
        }
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
    public int getDamage(){return DAMAGE;}

    public void setAnimationService(AnimationService animationService){
        this.animationService =  animationService;
    }

    public void setDamage(int damage) {
        this.DAMAGE = damage;
    }
}
