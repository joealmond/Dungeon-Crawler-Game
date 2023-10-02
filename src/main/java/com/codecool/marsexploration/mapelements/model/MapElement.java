package com.codecool.marsexploration.mapelements.model;

public class MapElement extends Map {
    private String[][] representation;
    private String name;
    private int dimension;
    private String preferredLocationSymbol;

    public MapElement(String[][] representation, String name, int dimension) {
        this(representation, name, dimension, null);
    }

    public MapElement(String[][] representation, String name, int dimension, String preferredLocationSymbol) {
        super(representation);
        this.name = name;
        this.dimension = dimension;
        this.preferredLocationSymbol = preferredLocationSymbol;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

