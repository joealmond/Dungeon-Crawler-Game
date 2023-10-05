package com.codecool.dungeoncrawl.ui.keyeventhandler.menu;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ExitGame implements KeyHandler {
  public static final KeyCode code = KeyCode.ESCAPE;

  @Override
  public void perform(KeyEvent event, GameMap map) {
    if (code.equals(event.getCode())) {
      System.out.println("Thanks for playing with us! Bye!");
      System.exit(0);
    }
  }
}
