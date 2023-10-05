package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
        ui.setBackground(new Background(new BackgroundFill(Color.rgb(67, 46, 59), new CornerRadii(0), new Insets(0))));

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
            Label name = new Label(tileName);
            Label value = new Label(tileValue);
            name.setTextFill(Color.WHITESMOKE);
            value.setTextFill(Color.WHITESMOKE);
            ui.add(name, 0, i);
            ui.add(value, 1, i);
        }
    }
}
