package com.jpmc.theater.Object;

import com.jpmc.theater.Object.Customer;
import com.jpmc.theater.Object.Movie;
import com.jpmc.theater.Object.Reservation;
import com.jpmc.theater.Object.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTest {

    @Test
    void testTotalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0))
        );
        assertTrue(new Reservation(customer, showing, 3).getTotalFee() == 28.5);
    }
}
