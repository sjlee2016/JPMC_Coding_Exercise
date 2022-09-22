package com.jpmc.theater.Service;

import com.jpmc.theater.Object.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ReservationServiceTest {

    @Test
    void testReserveSuccess(){
        LocalDateProvider provider = LocalDateProvider.singleton(LocalDate.of(2022,9,21));
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        List<Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(11, 0)))
        );
        Theater theater = new Theater(LocalDateProvider.singleton(), schedule);
        ReservationService reservationService = new ReservationService(theater);
        Reservation reservation = reservationService.reserve(new Customer("test-customer","test-id"),1,2);
        assertNotNull(reservation);
    }

    @Test
    void testReserveFailure(){
        LocalDateProvider provider = LocalDateProvider.singleton(LocalDate.of(2022,9,21));
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        List<Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(11, 0)))
                );
        Theater theater = new Theater(LocalDateProvider.singleton(), schedule);
        ReservationService reservationService = new ReservationService(theater);
        assertThrows( IllegalArgumentException.class, () -> reservationService.reserve(new Customer("test-customer","test-id"),1,0));
        assertThrows( IllegalArgumentException.class, () -> reservationService.reserve(new Customer("test-customer","test-id"),0,1));
    }
}
