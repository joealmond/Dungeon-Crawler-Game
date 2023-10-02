package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;


public interface MapGenerator {
    Map generate(MapConfiguration mapConfig);
}
