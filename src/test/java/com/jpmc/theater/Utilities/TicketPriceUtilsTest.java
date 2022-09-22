package com.jpmc.theater.Utilities;

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


    @Test
    @DisplayName("test multiple discounts") // test that when multiple discount applies, only biggest discount is applied
    void testMultipleDiscounts(){
        // Test 1   special discount, sequenceDiscount and timeDiscount all applies
        // special discount = $2   sequence discount = $3   and time discount = $2.5
        Movie testMovie = new Movie("test-movie", Duration.ofMinutes(90),10, 1);
        Showing showing = new Showing(testMovie, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(13,0)));
        assertEquals(7, TicketPriceUtils.calculateTotalFee(showing,1));
        // Test 2
        // special discount = $2  sequence discount = $2  and time discount = $2.5
        Showing showing2 = new Showing(testMovie, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(13,0)));
        assertEquals(7.5, TicketPriceUtils.calculateTotalFee(showing2,1));
        // Test 3
        // special discount = $1  sequence discount = $2  and time discount = $1.25
        Movie testMovie2 = new Movie("test-movie-2", Duration.ofMinutes(90),5, 1);
        Showing showing3 = new Showing(testMovie2, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(13,0)));
        assertEquals(3.0, TicketPriceUtils.calculateTotalFee(showing3,1));
    }

    @Test
    @DisplayName("test ticket price is never negative")
    void testTicketPriceIsNotNegative(){
        // sequence discount = $3 and ticket price = $2
        Movie testMovie = new Movie("test-movie", Duration.ofMinutes(90),2, 0);
        Showing showing = new Showing(testMovie, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(13,0)));
        assertEquals(0, TicketPriceUtils.calculateTotalFee(showing,1)); // test that ticket price is not negative
    }
}
