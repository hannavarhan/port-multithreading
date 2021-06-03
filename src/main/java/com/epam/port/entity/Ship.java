package com.epam.port.entity;

import com.epam.port.util.ShipIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Ship implements Runnable {

    private static final Logger logger = LogManager.getLogger(Ship.class);
    private static final int SHIP_CAPACITY = 30;
    private final long shipId;
    private ShipTask shipTask;
    private State state;
    private int containerAmount;

    public Ship(ShipTask shipTask) {
        shipId = ShipIdGenerator.generateShapeId();
        this.shipTask = shipTask;
        state = State.ENTER;
        Random random = new Random();
        containerAmount = random.nextInt(SHIP_CAPACITY + 1);
        logger.info("New Ship with id {} and {} containers was created", shipId, containerAmount);
    }

    public enum State {
        ENTER, PROCESS, FINISH
    }

    public enum ShipTask {
        LOAD, UNLOAD
    }

    public long getShipId() {
        return shipId;
    }

    public ShipTask getShipTask() {
        return shipTask;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getContainerAmount() {
        return containerAmount;
    }

    @Override
    public void run() {
        logger.info("Ship {} started {}", shipId, shipTask);
        Port port = Port.getInstance();
        Pier pier = port.moorPier();
        pier.processShip(this);
        port.leavePier(pier);
        logger.info("Ship {} finished {}", shipId, shipTask);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("shipId=").append(shipId);
        sb.append(", shipTask=").append(shipTask);
        sb.append(", state=").append(state);
        sb.append(", containerAmount=").append(containerAmount);
        sb.append('}');
        return sb.toString();
    }

//TODO equals & hashcode

}
