package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;

public interface MapConfigurationValidator {
    boolean validate(MapConfiguration mapConfig);
}
