package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.mapelements.model.MapElement;

public interface MapElementBuilder {
    MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol);
}
