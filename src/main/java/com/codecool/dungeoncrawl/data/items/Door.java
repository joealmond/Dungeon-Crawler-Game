package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;

public class Door extends Item {
  public Door(Cell cell) {
    super(cell, false);
  }

  @Override
  public String getTileName() {
    return "door";
  }

  @Override
  public <T> void interactWithPlayer(T cell) {
    System.out.println(cell);
  }
}
