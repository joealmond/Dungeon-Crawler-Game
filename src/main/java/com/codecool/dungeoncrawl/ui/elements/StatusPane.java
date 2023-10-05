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

    public StatusPane() {
        ui = new GridPane();
    }

    public BorderPane build() {
        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public GridPane getUi() {
        return ui;
    }

    public void setLabels(List<String> labalNames) {
        for (int i = 0; i < labalNames.size() - 1; i+=2) {
            String tileName = labalNames.get(i);
            String tileValue = labalNames.get(i + 1);
            System.out.println(tileName);
            System.out.println(tileValue);
            ui.add(new Label(tileName), 0, i);
            ui.add(new Label(tileValue), 1, i);
        }
    }

}
