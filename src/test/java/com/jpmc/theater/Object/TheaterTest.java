package com.jpmc.theater.Object;

import com.jpmc.theater.Object.Theater;
import com.jpmc.theater.Service.LocalDateProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTest {

    @Test
    void testPrintMovieSchedule() {
        String expected = "2022-09-21\n===================================================\n1: 2022-09-21T09:00 Turning Red (1 hour 25 minutes) $11.0\n2: 2022-09-21T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n3: 2022-09-21T12:50 The Batman (1 hour 35 minutes) $9.0\n4: 2022-09-21T14:30 Turning Red (1 hour 25 minutes) $11.0\n5: 2022-09-21T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n6: 2022-09-21T17:50 The Batman (1 hour 35 minutes) $9.0\n7: 2022-09-21T19:30 Turning Red (1 hour 25 minutes) $11.0\n8: 2022-09-21T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n9: 2022-09-21T23:00 The Batman (1 hour 35 minutes) $9.0\n===================================================\n";
        Theater theater = new Theater(LocalDateProvider.singleton());
        assertEquals(expected, theater.toString());
    }
}
