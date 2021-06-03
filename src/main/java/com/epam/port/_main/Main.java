package com.epam.port._main;

import com.epam.port.entity.Ship;
import com.epam.port.exception.PortException;
import com.epam.port.parser.ShipActionParser;
import com.epam.port.reader.ShipFileReader;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws PortException {
        ShipFileReader reader = new ShipFileReader();
        ArrayList<String> shipStrings = reader.readFile("data/data.txt");
        ShipActionParser parser = new ShipActionParser();
        ArrayList<Ship> ships = parser.parseShipAction(shipStrings);

        ExecutorService executorService = Executors.newFixedThreadPool(ships.size());
        ships.forEach(executorService::execute);
        executorService.shutdown();

    }
}
