package com.codecool.dungeoncrawl.ui.keyeventhandler.movement;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Up implements KeyHandler {
    public static final KeyCode code = KeyCode.UP;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if(code.equals(event.getCode()))
            map.getPlayer().move(0, -1);
    }
}
