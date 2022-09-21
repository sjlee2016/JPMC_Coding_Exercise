package com.jpmc.theater;

import com.jpmc.theater.Object.Customer;
import com.jpmc.theater.Object.Movie;
import com.jpmc.theater.Object.Showing;
import com.jpmc.theater.Utilities.TicketPriceUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketPriceUtilsTest {

    @Test
    @DisplayName("test sequence discount")
    void testSequenceDiscount() {
        Movie testMovie = new Movie("test-movie", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(testMovie, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        Showing showing2 = new Showing(testMovie, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        Showing showing3 = new Showing(testMovie, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        Showing showing4 = new Showing(testMovie, 7, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        assertEquals(9.5, TicketPriceUtils.calculateTotalFee(showing,1));
        assertEquals(10.5, TicketPriceUtils.calculateTotalFee(showing2,1));
        assertEquals(12.5, TicketPriceUtils.calculateTotalFee(showing3,1));
        assertEquals(11.5, TicketPriceUtils.calculateTotalFee(showing4,1));
    }

    @Test
    @DisplayName("test time discount")
    void testTimeDiscount() {
        Movie testMovie = new Movie("test-movie", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(testMovie, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(13,0)));
        Showing showing2 = new Showing(testMovie, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        assertEquals(9.375, TicketPriceUtils.calculateTotalFee(showing,1));
        assertEquals(10, TicketPriceUtils.calculateTotalFee(showing2,1));

    }

    @Test
    @DisplayName("test special discount")
    void testSpecialDiscount(){
        Movie testMovie = new Movie("test-movie", Duration.ofMinutes(90),12.5, 1);
        Movie testMovie2 = new Movie("test-movie-2", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(testMovie, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        Showing showing2 = new Showing(testMovie2, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        assertEquals(10, TicketPriceUtils.calculateTotalFee(showing,1));
        assertEquals(12.5, TicketPriceUtils.calculateTotalFee(showing2,1));
    }
}
