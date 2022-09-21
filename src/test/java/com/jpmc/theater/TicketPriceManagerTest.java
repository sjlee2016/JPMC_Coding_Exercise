package com.jpmc.theater;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketPriceManagerTest {

    @Test
    @DisplayName("test sequence discount")
    void testSequenceDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0)));
        Customer customer = new Customer("Se Jin", "unique-id");
        TicketPriceManager ticketPriceManager = new TicketPriceManager(spiderMan,showing,customer,1);
        assertEquals(10, ticketPriceManager.calculateTotalFee());
        System.out.println(Duration.ofMinutes(90));
    }
}
