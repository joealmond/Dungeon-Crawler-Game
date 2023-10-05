package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.inventory.Inventory;

import java.util.Objects;

public class Farmer extends Item {
  public Farmer(Cell cell) {
    super(cell, false);
  }

  @Override
  public String getTileName() {
    return "farmer";
  }

  @Override
  public void interactWithPlayer(Player player) {
    if (isItemInInventory(player.getInventory())) {
      player.setCurrentHealth(25);
    }
  }

  private boolean isItemInInventory(Inventory inventory) {
    return inventory.getItems()
      .stream()
      .filter(item -> Objects.equals(item.getTileName(), "chicken"))
      .count() == 1;
  }
}
