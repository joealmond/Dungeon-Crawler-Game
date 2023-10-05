package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.utility.ItemInventoryUtil;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    List<Item> chicken = player.getInventory().getItems()
     .stream()
     .filter(item -> Objects.equals(item.getTileName(), "chicken"))
     .collect(Collectors.toList());

    if (ItemInventoryUtil.isItemInInventory(player.getInventory(), "chicken")) {
      player.setCurrentHealth(25);
      player.removeItemFromInventory(chicken.get(0));
    }
  }
}
