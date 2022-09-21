package com.jpmc.theater.Object;

import com.jpmc.theater.Object.LocalDateProvider;
import org.junit.jupiter.api.Test;

public class LocalDateProviderTest {
    @Test
    void testCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().getCurrentDate());
    }
}
