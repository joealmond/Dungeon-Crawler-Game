package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.logic.utility.ItemPlayerInteractionUtil;

public class Sword extends Item {
  private static final int DAMAGE_VALUE = 3;

  public Sword(Cell cell) {
    super(cell, true);
  }

  @Override
  public String getTileName() {
    return "sword";
  }

  @Override
  public void interactWithPlayer(Player player) {
    ItemPlayerInteractionUtil.defaultUpdateAfterItemIsPickedUp(this, player, cell);
    player.setDamage(DAMAGE_VALUE);
    player.setActiveWeapon(getTileName().substring(0, 1).toUpperCase() + getTileName().substring(1));
  }
}
