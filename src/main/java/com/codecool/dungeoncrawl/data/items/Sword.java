package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Player;

public class Sword extends Item {
  public Sword(Cell cell) {
    super(cell, true);
  }

  @Override
  public String getTileName() {
    return "sword";
  }

  @Override
  public void interactWithPlayer(Player player) {

  }
}
