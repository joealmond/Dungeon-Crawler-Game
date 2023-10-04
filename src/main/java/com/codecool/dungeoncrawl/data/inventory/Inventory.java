package com.codecool.dungeoncrawl.data.inventory;

import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventory {
  private final List<Item> items;

  public Inventory() {
    this.items = new ArrayList<>();
  }

  public List<Item> getItems() {
    return items;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public boolean hasTorch() {
    return items.stream()
      .filter((item) -> Objects.equals(item.getTileName(), "torch"))
      .count() == 1;
  }

  @Override
  public String toString() {
      return String.format("%s", items);
  }
}
