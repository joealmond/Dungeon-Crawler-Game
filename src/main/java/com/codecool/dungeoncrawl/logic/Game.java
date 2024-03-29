package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.ui.keyeventhandler.attack.A;
import com.codecool.dungeoncrawl.ui.keyeventhandler.attack.D;
import com.codecool.dungeoncrawl.ui.keyeventhandler.attack.S;
import com.codecool.dungeoncrawl.ui.keyeventhandler.attack.W;
import com.codecool.dungeoncrawl.ui.keyeventhandler.item.P;
import com.codecool.dungeoncrawl.ui.keyeventhandler.menu.ExitGame;
import com.codecool.dungeoncrawl.ui.keyeventhandler.movement.Down;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import com.codecool.dungeoncrawl.ui.keyeventhandler.movement.Left;
import com.codecool.dungeoncrawl.ui.keyeventhandler.movement.Right;
import com.codecool.dungeoncrawl.ui.keyeventhandler.movement.Up;
import com.codecool.dungeoncrawl.ui.UI;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Set;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;
    private AnimationService animationService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right(), new W(), new S(), new A(), new D(), new P(), new ExitGame());
        this.logic = new GameLogic();
        this.ui = new UI(logic, keyHandlers);
        this.animationService = new AnimationService(ui,logic);

        logic.getActors().forEach(actor -> actor.setAnimationService(animationService));
        logic.getItems().forEach(item -> item.setAnimationService(animationService));
        logic.getItems().forEach(item -> item.setLogic(logic));

        ui.setUpPain(primaryStage);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        animationService.startMonsterMovement();
    }
}
