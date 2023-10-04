package com.codecool.dungeoncrawl.ui.keyeventhandler.attack;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class W implements KeyHandler {
    public static final KeyCode code = KeyCode.W;
    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code.equals(event.getCode()))
            map.getPlayer().attack(0, -1);
    }
}
