package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
  private Cell cell;

  public Item(Cell cell) {
    this.cell = cell;
    this.cell.setItem(this);
  }
}
