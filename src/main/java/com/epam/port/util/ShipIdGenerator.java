package com.epam.port.util;

public class ShipIdGenerator {

    private static long counter;

    private ShipIdGenerator() {
    }

    public static long generateShapeId() {
        return counter++;
    }
}
