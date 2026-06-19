package com.eskcti.algasensors.temperature.processing;

import java.time.*;
import java.util.UUID;

public class UUIDv7Utils {
    private UUIDv7Utils() {
        // Private constructor to prevent instantiation
    }

    public static OffsetDateTime extractOffsetDateTimeFromUUIDv7(UUID uuid) {
        // Implementation for extracting offset date time from UUIDv7
        if (uuid.version() != 7) {
            throw new IllegalArgumentException("UUID must be of version 7");
        }
        long timestamp = uuid.getMostSignificantBits() >>> 16; // Convert to milliseconds
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
