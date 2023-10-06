package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.utility.ItemPlayerInteractionUtil;

public class Key extends Item {
  public Key(Cell cell) {
    super(cell, true);
  }

  @Override
  public String getTileName() {
    return "key";
  }

  @Override
  public void interactWithPlayer(Player player) {
    ItemPlayerInteractionUtil.defaultUpdateAfterItemIsPickedUp(this, player, cell);
  }
}
