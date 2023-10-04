package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Key extends Item {
  public Key(Cell cell) {
    super(cell, true);
  }

  @Override
  public String getTileName() {
    return "key";
  }

  @Override
  public <T> void interactWithPlayer(T cell) {
    System.out.println(cell);
  }
}
