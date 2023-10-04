package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;

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
    player.addToInventory(this);
    cell.setItem(null);
    cell.setType(CellType.FLOOR);
  }
}
