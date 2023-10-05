package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.*;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label healthTextLabel;
    private Label healthValueLabel;
    private Label activeWeaponTextLabel;
    private Label activeWeaponValueLabel;
    private Label itemsTextLabel;
    private Map<Label,Label> itemLabels;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        activeWeaponTextLabel = new Label("Active weapon: ");
        activeWeaponValueLabel = new Label();
        itemsTextLabel = new Label("Item Inventory: ");
        itemLabels = new LinkedHashMap<>();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(activeWeaponTextLabel, 0, 1);
        ui.add(activeWeaponValueLabel, 1, 1);
        ui.add(itemsTextLabel, 0, 2);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValue(String text) {
        healthValueLabel.setText(text);
    }

    public void setActiveWeaponValue(String text) {
        activeWeaponValueLabel.setText(text);
    }

    public void setItemLabels(List<String> tileNames) {
        itemLabels.clear();
        int count = 1;
        for (String tileName : tileNames) {
            itemLabels.put(new Label("item " + count + ": "), new Label(tileName));
            count++;
        }
        showItemLabels();
    }

    private void showItemLabels() {
        int row = 2;
        for (Map.Entry<Label, Label> entry : itemLabels.entrySet()) {
            ui.add(entry.getKey(), 0, row);
            ui.add(entry.getValue(), 1, row);
            row++;
        }
    }
}
