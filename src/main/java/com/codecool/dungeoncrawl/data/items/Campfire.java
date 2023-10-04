package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Campfire extends Item {
  public Campfire(Cell cell) {
    super(cell, false);
  }

  @Override
  public String getTileName() {
    return "campfire";
  }

  @Override
  public void interactWithPlayer(Player player) {
    if (player.getCurrentHealth() < player.getMaxHealth()) {
      player.setCurrentHealth(player.getCurrentHealth() + 1);
    }
  }
}
