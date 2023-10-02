package com.codecool.marsexploration.configuration.model;

import java.util.List;

public record MapConfiguration(
        int mapSize,
        double elementToSpaceRatio,
        List<MapElementConfiguration> mapElementConfigurations) {}
