package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.utility.ItemPlayerInteractionUtil;

public class HealthPotion extends Item {
  private static final int AMOUNT_OF_HEALING = 7;

  public HealthPotion(Cell cell) {
    super(cell, true);
  }

  @Override
  public String getTileName() {
    return "health-potion";
  }

  @Override
  public void interactWithPlayer(Player player) {
    ItemPlayerInteractionUtil.defaultUpdateAfterItemIsPickedUp(this, player, cell);
  }

  public void usePotion(Player player) {
    int amountOfMissingHp = player.getMaxHealth() - player.getCurrentHealth();

    if (amountOfMissingHp <= AMOUNT_OF_HEALING) {
      player.setCurrentHealth(player.getCurrentHealth() + amountOfMissingHp);
    } else {
      player.setCurrentHealth(player.getCurrentHealth() + AMOUNT_OF_HEALING);
    }

    player.removeItemFromInventory(this);
  }
}
