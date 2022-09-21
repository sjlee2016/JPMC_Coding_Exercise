package com.jpmc.theater.Object;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTest {

    @Test
    void testPrintMovieSchedule() {
        LocalDateProvider provider = LocalDateProvider.singleton();
        String expected = "2022-09-21\n===================================================\n1: 2022-09-21T09:00 Turning Red (1 hour 25 minutes) $11.0\n2: 2022-09-21T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n3: 2022-09-21T12:50 The Batman (1 hour 35 minutes) $9.0\n4: 2022-09-21T14:30 Turning Red (1 hour 25 minutes) $11.0\n5: 2022-09-21T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n6: 2022-09-21T17:50 The Batman (1 hour 35 minutes) $9.0\n7: 2022-09-21T19:30 Turning Red (1 hour 25 minutes) $11.0\n8: 2022-09-21T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n9: 2022-09-21T23:00 The Batman (1 hour 35 minutes) $9.0\n===================================================\n";
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        List <Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(23, 0)))
        );
        Theater theater = new Theater(LocalDateProvider.singleton(), schedule);
        assertEquals(expected, theater.toString());
    }

    @Test
    void testToJSON(){
        LocalDateProvider provider = LocalDateProvider.singleton(LocalDate.of(2022,9,21));
        String expected = "2022-09-21\n===================================================\n1: 2022-09-21T09:00 Turning Red (1 hour 25 minutes) $11.0\n2: 2022-09-21T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n3: 2022-09-21T12:50 The Batman (1 hour 35 minutes) $9.0\n4: 2022-09-21T14:30 Turning Red (1 hour 25 minutes) $11.0\n5: 2022-09-21T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n6: 2022-09-21T17:50 The Batman (1 hour 35 minutes) $9.0\n7: 2022-09-21T19:30 Turning Red (1 hour 25 minutes) $11.0\n8: 2022-09-21T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n9: 2022-09-21T23:00 The Batman (1 hour 35 minutes) $9.0\n===================================================\n";
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        List <Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(23, 0)))
        );

        Theater theater = new Theater(LocalDateProvider.singleton(), schedule);
    }
}
