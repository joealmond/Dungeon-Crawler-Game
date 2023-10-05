package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;

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
    player.addItemToInventory(this);
    cell.setItem(null);
    cell.setType(CellType.FLOOR);
  }
}
