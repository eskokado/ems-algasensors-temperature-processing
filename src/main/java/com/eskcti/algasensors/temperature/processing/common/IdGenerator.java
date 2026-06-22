package com.eskcti.algasensors.temperature.processing.common;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

public class IdGenerator {
    private static final TimeBasedEpochRandomGenerator timeBasedEpochRandomGenerator = 
        Generators.timeBasedEpochRandomGenerator();
    private IdGenerator() {
        // Private constructor to prevent instantiation
    }

    public static UUID generateTimeBasedUUID() {
        return timeBasedEpochRandomGenerator.generate();
    }

}
