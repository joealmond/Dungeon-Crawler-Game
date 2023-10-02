package com.codecool.marsexploration.configuration.model;

import java.util.List;

public record MapElementConfiguration(
        String symbol,
        String name,
        List<ElementToSize> elementToSizes,
        int dimensionGrowth,
        String preferredLocationSymbol) {}
