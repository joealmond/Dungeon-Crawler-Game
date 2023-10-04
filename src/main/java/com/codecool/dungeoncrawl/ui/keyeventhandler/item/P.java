package com.codecool.dungeoncrawl.ui.keyeventhandler.item;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.items.HealthPotion;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class P implements KeyHandler {
  public static final KeyCode code = KeyCode.P;

  @Override
  public void perform(KeyEvent event, GameMap map) {
    if (code.equals(event.getCode())) {
      map.getPlayer().getInventory().getItems()
       .stream()
       .filter((item) -> Objects.equals(item.getTileName(), "health-potion"))
       .findFirst()
       .ifPresent(item -> ((HealthPotion)item).usePotion(map.getPlayer()));
    }
  }
}
