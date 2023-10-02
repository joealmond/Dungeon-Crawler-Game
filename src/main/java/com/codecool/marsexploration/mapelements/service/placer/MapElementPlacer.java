package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;

public interface MapElementPlacer {
    boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate);
    void placeElement(MapElement element, String[][] map, Coordinate coordinate);
}
