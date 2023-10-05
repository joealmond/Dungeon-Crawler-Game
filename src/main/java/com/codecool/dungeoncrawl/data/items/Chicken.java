package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.utility.ItemPlayerInteractionUtil;

public class Chicken extends Item {
  public Chicken(Cell cell) {
    super(cell, true);
  }

  @Override
  public String getTileName() {
    return "chicken";
  }

  @Override
  public void interactWithPlayer(Player player) {
    ItemPlayerInteractionUtil.defaultUpdateAfterItemIsPickedUp(this, player, cell);
  }
}
