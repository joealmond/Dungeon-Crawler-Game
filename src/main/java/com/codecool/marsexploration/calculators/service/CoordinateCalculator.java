package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

public interface CoordinateCalculator {
    Coordinate getRandomCoordinate(int dimension);
    Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
    Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension);
}
