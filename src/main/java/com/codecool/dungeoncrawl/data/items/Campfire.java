package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Campfire extends Item {
  public Campfire(Cell cell) {
    super(cell, false);
  }

  @Override
  public String getTileName() {
    return "campfire";
  }

  @Override
  public <T> void interactWithPlayer(T cell) {
    System.out.println(cell);
  }
}
