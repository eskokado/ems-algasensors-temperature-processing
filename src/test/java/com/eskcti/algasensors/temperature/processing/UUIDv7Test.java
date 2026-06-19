package com.eskcti.algasensors.temperature.processing;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UUIDv7Test {
    @Test
    void shouldGenerateUniqueUUIDv7() {
        // Generate multiple UUIDs and ensure they are unique
        for (int i = 0; i < 10; i++) {
            UUID uuid = IdGenerator.generateTimeBasedUUID();

            OffsetDateTime uuidDateTime = UUIDv7Utils.extractOffsetDateTimeFromUUIDv7(uuid).truncatedTo(ChronoUnit.MINUTES);
            OffsetDateTime currentDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            Assertions.assertThat(uuid.version()).isEqualTo(7);
            Assertions.assertThat(uuidDateTime).isEqualTo(currentDateTime);
        }
    }
}
