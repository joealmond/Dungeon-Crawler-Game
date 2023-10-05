package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.AnimationService;
import com.codecool.dungeoncrawl.logic.GameLogic;

public abstract class Item implements Drawable {
  protected Cell cell;
  private final boolean isPickable;

  protected GameLogic logic;
  protected AnimationService animationService;

  public Item(Cell cell, boolean isPickable) {
    this.cell = cell;
    this.cell.setItem(this);
    this.isPickable = isPickable;
  }

  public void setLogic(GameLogic logic){
    this.logic = logic;
  }
  public Cell getCell() {
    return cell;
  }

  public void setCell(Cell cell) {
    this.cell = cell;
  }

  public boolean isPickable() {
    return isPickable;
  }

  public abstract void interactWithPlayer(Player player);

  public void setAnimationService(AnimationService animationService) {
    this.animationService = animationService;
  }
}
