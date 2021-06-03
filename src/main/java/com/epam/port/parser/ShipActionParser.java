package com.epam.port.parser;

import com.epam.port.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShipActionParser {
    private final static Logger logger = LogManager.getLogger(ShipActionParser.class);

    public ArrayList<Ship> parseShipAction(ArrayList<String> stringActions) {
        ArrayList<Ship> result = stringActions.stream()
                .map(String::toUpperCase)
                .map(Ship.ShipTask::valueOf)
                .map(Ship::new)
                .collect(Collectors.toCollection(ArrayList::new));
        logger.info("Ships from parseMethod: {}", result);
        return result;
    }

}
