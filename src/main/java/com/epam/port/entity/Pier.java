package com.epam.port.entity;

import com.epam.port.util.PierIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pier {

    private static final Logger logger = LogManager.getLogger(Pier.class);
    private static final int MIN_MILLISECONDS = 1;
    private static final int MAX_MILLISECONDS = 1000;
    private final long pierId;

    public Pier() {
        pierId = PierIdGenerator.generatePierId();
        logger.info("new Pier with id {} was created", pierId);
    }

    public long getPierId() {
        return pierId;
    }

    public void processShip(Ship ship) {
        logger.info("Pier {} started {} of Ship {} with {} containers", pierId, ship.getShipTask(), ship.getShipId(),
                ship.getContainerAmount());
        ship.setState(Ship.State.PROCESS);
        Random random = new Random();
        int timeout = random.nextInt(MAX_MILLISECONDS - MIN_MILLISECONDS + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            logger.error("Exception in Pier: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        Ship.ShipTask currentTask = ship.getShipTask();
        Port port = Port.getInstance();
        switch (currentTask) {
            case LOAD -> port.loadContainers(ship.getContainerAmount());
            case UNLOAD -> port.unloadContainers(ship.getContainerAmount());
        }
        ship.setState(Ship.State.FINISH);
        logger.info("Pier {} finished {} of Ship {}", pierId, currentTask, ship.getShipId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pier{");
        sb.append("pierId=").append(pierId);
        sb.append('}');
        return sb.toString();
    }

    //TODO equals & hashcode
}
