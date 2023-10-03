package com.codecool.dungeoncrawl.logic;


import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.gamesetup.Constants;
import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Down;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Left;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Right;
import com.codecool.dungeoncrawl.ui.keyeventhandler.Up;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.util.stream.Collectors;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;
    private MonsterMovementService monsterMovementService;
    private Inventory inventory;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right());
        this.inventory = new Inventory();
        this.logic = new GameLogic();
        this.ui = new UI(logic, keyHandlers);
        this.monsterMovementService = new MonsterMovementService(ui,logic);
        this.ui = new UI(logic, keyHandlers, inventory);

        ui.setUpPain(primaryStage);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
        monsterMovementService.startMonsterMovement();
    }
}
