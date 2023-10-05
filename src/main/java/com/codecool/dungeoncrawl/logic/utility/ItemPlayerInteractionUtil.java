package com.codecool.dungeoncrawl.logic.utility;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Item;

public final class ItemPlayerInteractionUtil {
  private ItemPlayerInteractionUtil() {}

  public static void defaultUpdateAfterItemIsPickedUp(Item item, Player player, Cell cell) {
    player.addItemToInventory(item);
    cell.setItem(null);
    cell.setType(CellType.FLOOR);
  }
}
