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
        double sequenceDiscount = 0;
        switch (showing.getSequenceOfTheDay()) {
            case 1:
                sequenceDiscount = 3;
                break;
            case 2:
                sequenceDiscount = 2;
                break;
            case 7:
                sequenceDiscount = 1;
                break;
            default:
                sequenceDiscount = 0;
                break;
        }
        return sequenceDiscount;
    }

    private double getSpecialDiscount(){
        double specialDiscount = 0;
        double originalPrice = movie.getTicketPrice();
        if(MOVIE_CODE_SPECIAL == movie.getSpecialCode()){
            specialDiscount = originalPrice * SPECIAL_DISCOUNT;
        }
        return specialDiscount;
    }

    private double getTimeDiscount(){
        double timeDiscount = 0;
        double originalPrice = movie.getTicketPrice();
        if(isBetweenHours(LocalTime.of(11,0), LocalTime.of(16,0))){
            timeDiscount = originalPrice * TIME_DISCOUNT;
        }
        return timeDiscount;
    }
    public double calculateTotalFee() {
        double originalPrice = movie.getTicketPrice();
        double discount  = max(this.getSequenceDiscount() ,max(this.getSpecialDiscount(), this.getTimeDiscount()));
        return (originalPrice - discount) * numOfTickets;
    }

}
