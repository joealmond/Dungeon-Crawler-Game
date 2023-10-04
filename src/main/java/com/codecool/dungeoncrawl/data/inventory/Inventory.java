package com.codecool.dungeoncrawl.data.inventory;

import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;

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

  @Override
  public String toString() {
      return String.format("%s", items);
  }
}
