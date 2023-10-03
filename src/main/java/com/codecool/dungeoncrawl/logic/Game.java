package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Down;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Left;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Right;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Up;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Set;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;
    private Inventory inventory;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right());
        this.inventory = new Inventory();
        this.logic = new GameLogic();
        this.ui = new UI(logic, keyHandlers, inventory);
        ui.setUpPain(primaryStage);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
}
