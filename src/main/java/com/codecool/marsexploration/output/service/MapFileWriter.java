package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.Map;

public interface MapFileWriter
{
    void writeMapFile(Map map, String file);
}