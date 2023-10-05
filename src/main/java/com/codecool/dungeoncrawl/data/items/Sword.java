package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Player;

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
    player.addItemToInventory(this);
    cell.setItem(null);
    cell.setType(CellType.FLOOR);
    player.setDamage(DAMAGE_VALUE);
    player.setActiveWeapon(getTileName().substring(0, 1).toUpperCase() + getTileName().substring(1));
  }
}
