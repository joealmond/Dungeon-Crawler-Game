package com.codecool.dungeoncrawl.data.items;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Item implements Drawable {
  private Cell cell;
  private boolean isPickable;

  public Item(Cell cell, boolean isPickable) {
    this.cell = cell;
    this.cell.setItem(this);
    this.isPickable = isPickable;
  }

  public Cell getCell() {
    return cell;
  }

  public void setCell(Cell cell) {
    this.cell = cell;
  }

  public boolean isPickable() {
    return isPickable;
  }

  public void setPickable(boolean pickable) {
    isPickable = pickable;
  }
}
