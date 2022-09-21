package com.jpmc.theater;

import java.time.LocalTime;

import static java.lang.Math.max;

public class TicketPriceManager {
    private static int MOVIE_CODE_SPECIAL = 1;
    private static double SPECIAL_DISCOUNT = 0.2;
    private static double TIME_DISCOUNT = 0.25;

    private Movie movie;
    private Showing showing;
    private Customer customer;
    private int numOfTickets;
    public TicketPriceManager(Movie movie, Showing showing, Customer customer, int numOfTickets){
        this.movie = movie;
        this.showing = showing;
        this.customer = customer;
        this.numOfTickets = numOfTickets;
    }

    private boolean isBetweenHours(LocalTime start, LocalTime end){
        LocalTime currentTime = showing.getStartTime().toLocalTime();
        return currentTime.isAfter(start) && currentTime.isBefore(end);
    }

    private double getSequenceDiscount() {
        switch (showing.getSequenceOfTheDay()) {
            case 1: return 3;
            case 2: return 2;
            case 7:
                return 1;
        }
        return 0;
    }

    private double getSpecialDiscount(){
        if(MOVIE_CODE_SPECIAL == movie.getSpecialCode()){
            return movie.getTicketPrice() * SPECIAL_DISCOUNT;
        }
        return 0;
    }

    private double getTimeDiscount(){
        if(isBetweenHours(LocalTime.of(11,0), LocalTime.of(16,0))){
            return movie.getTicketPrice() * TIME_DISCOUNT;
        }
        return 0;
    }
    public double calculateTotalFee() {
        double originalPrice = movie.getTicketPrice();
        double discount  = max(this.getSequenceDiscount() ,max(this.getSpecialDiscount(), this.getTimeDiscount()));
        return (originalPrice - discount) * numOfTickets;
    }

}
