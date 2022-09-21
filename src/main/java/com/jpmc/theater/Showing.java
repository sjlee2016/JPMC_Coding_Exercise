package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.lang.Math.max;

public class Showing {
    private static int MOVIE_CODE_SPECIAL = 1;
    private static double SPECIAL_DISCOUNT = 0.2;
    private static double TIME_DISCOUNT = 0.25;

    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    private boolean isBetweenHours(LocalTime start, LocalTime end){
        LocalTime currentTime = getStartTime().toLocalTime();
        System.out.println(currentTime);
        System.out.println(currentTime.getHour());
        return currentTime.isAfter(start) && currentTime.isBefore(end);
    }

    private double getSequenceDiscount() {
        double sequenceDiscount = 0;
        switch (sequenceOfTheDay) {
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
    public double calculateFee(int audienceCount) {
        double originalPrice = movie.getTicketPrice();
        double discount  = max(this.getSequenceDiscount() ,max(this.getSpecialDiscount(), this.getTimeDiscount()));

        return (originalPrice - discount) * audienceCount;
    }
}
