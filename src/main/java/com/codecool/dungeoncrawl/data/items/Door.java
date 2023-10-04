package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.inventory.Inventory;

import java.util.Objects;

public class Door extends Item {
  public Door(Cell cell) {
    super(cell, false);
  }

  @Override
  public String getTileName() {
    return "door";
  }

  @Override
  public void interactWithPlayer(Player player) {
    System.out.println("Entered door");

    if (isItemInInventory(player.getInventory())) {
      cell.getItem().getCell().setType(CellType.OPENED_DOOR);
    }
  }

  private boolean isItemInInventory(Inventory inventory) {
    return inventory.getItems()
      .stream()
      .filter(item -> Objects.equals(item.getTileName(), "key"))
      .count() == 1;
  }
}
