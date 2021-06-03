package com.epam.port.util;

public class PierIdGenerator {

    private static long counter;

    private PierIdGenerator() {
    }

    public static long generatePierId() {
        return counter++;
    }
}
