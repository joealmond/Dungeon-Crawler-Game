package com.codecool.dungeoncrawl.logic.utility;

import com.codecool.dungeoncrawl.data.inventory.Inventory;

import java.util.Objects;

public final class ItemInventoryUtil {
  private ItemInventoryUtil() {}

  public static boolean isItemInInventory(Inventory inventory, String nameOfItem) {
    return inventory.getItems()
      .stream()
      .filter(item -> Objects.equals(item.getTileName(), nameOfItem))
      .count() == 1;
  }
}
