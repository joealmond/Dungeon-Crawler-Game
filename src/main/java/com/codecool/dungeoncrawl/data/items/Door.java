package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.utility.ItemInventoryUtil;

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
    if (ItemInventoryUtil.isItemInInventory(player.getInventory(), "key")) {
      cell.getItem().getCell().setType(CellType.OPENED_DOOR);
      logic.generateNewMap();

      logic.getActors().forEach(actor -> actor.setAnimationService(animationService));
      logic.getItems().forEach(item -> item.setAnimationService(animationService));
      logic.getItems().forEach(item -> item.setLogic(logic));
    }
  }
}
