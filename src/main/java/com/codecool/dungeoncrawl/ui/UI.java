package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.items.Item;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.codecool.dungeoncrawl.data.gamesetup.Constants.LINE_OF_SIGHT;
import static com.codecool.dungeoncrawl.data.gamesetup.Constants.LINE_OF_SIGHT_WITH_TORCH;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;

    private MainStage mainStage;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;

    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(logic.getMapWidth() * Tiles.TILE_WIDTH, logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
    }

    public void setUpPain(Stage primaryStage) {
        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);
        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic.getMap());
        }

        refresh();
    }

    public void refresh() {
        mainStage.getStatusPane().getUi().getChildren().clear();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int playerX = logic.getPlayerX();
        int playerY = logic.getPlayerY();
        int playerLineOfSight = LINE_OF_SIGHT;

        if (logic.getPlayerInventory().hasTorch()) {
            playerLineOfSight = LINE_OF_SIGHT_WITH_TORCH;
        }

        for (int x = Math.max(playerX - playerLineOfSight, 0); x < Math.min(playerX + playerLineOfSight, logic.getMapWidth()); x++) {
            for (int y = Math.max(playerY - playerLineOfSight, 0); y < Math.min(playerY + playerLineOfSight, logic.getMapHeight()); y++) {
                Cell cell = logic.getCell(x, y);

                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }

        mainStage.setLabels(getLabelNames(List.of(
                getBaseNames(List.of(
                    "Health: " , logic.getPlayerHealth(),
                    "Active Weapon: ", logic.getPlayerWeapon(),
                    "Item Inventory: " , " "
                )), getItemNames())));
    }

    private List<String> getLabelNames(List<List<String>> lists) {
        List<String> labelNames = new ArrayList<>();
        for (List<String> list : lists) {
            labelNames.addAll(list);
        }
        return labelNames;
    }

    private List<String> getBaseNames(List<String> list) {
        List<String> names = new ArrayList<>();
        if (!list.isEmpty()) {
            names.addAll(list);
        }
        return names;

    }

    private List<String> getItemNames() {
        List<String> tileNames = new ArrayList<>();
        if (!logic.getPlayerInventory().getItems().isEmpty()) {
            int count = 1;
            for (Item item : logic.getPlayerInventory().getItems()) {
                tileNames.add("Item " + count + ": ");
                tileNames.add(item.getTileName());
                count++;
            }
        }
        return tileNames;
    }
}
