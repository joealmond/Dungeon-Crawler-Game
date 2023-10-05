package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.utility.ItemPlayerInteractionUtil;

public class Torch extends Item {
  public Torch(Cell cell) {
    super(cell, true);
  }

  @Override
  public String getTileName() {
    return "torch";
  }

  @Override
  public void interactWithPlayer(Player player) {
    ItemPlayerInteractionUtil.defaultUpdateAfterItemIsPickedUp(this, player, cell);

  }
}
