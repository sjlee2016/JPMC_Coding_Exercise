package com.jpmc.theater;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTest {
    @Test
    void testCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }
}
