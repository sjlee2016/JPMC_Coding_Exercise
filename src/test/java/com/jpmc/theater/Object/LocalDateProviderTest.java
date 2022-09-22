package com.jpmc.theater.Object;

import com.jpmc.theater.Object.LocalDateProvider;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTest {
    @Test
    void testCurrentTime() {
        assertEquals(LocalDate.now(), LocalDateProvider.singleton().getCurrentDate());
        System.out.println("current time is - " + LocalDateProvider.singleton().getCurrentDate());
    }
}
