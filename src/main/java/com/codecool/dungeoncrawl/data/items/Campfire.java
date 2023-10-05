package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Campfire extends Item {
  private static final int AMOUNT_OF_HEALING = 1;

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
      player.setCurrentHealth(player.getCurrentHealth() + AMOUNT_OF_HEALING);
    }
  }
}
