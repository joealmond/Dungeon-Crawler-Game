package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;

public interface MapElementsGenerator {
    Iterable<MapElement> createAll(MapConfiguration mapConfig);
}
