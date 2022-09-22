package com.jpmc.theater.Utilities;

import com.jpmc.theater.Object.Showing;

import java.time.LocalTime;

import static java.lang.Math.max;
/**
 * Utility used to calculate ticket price of the movie
 */
public class TicketPriceUtils {
    private static int SPECIAL_MOVIE_CODE = 1;
    private static double SPECIAL_DISCOUNT = 0.2;
    private static double TIME_DISCOUNT = 0.25;


    private static boolean isBetweenHours(Showing showing, LocalTime start, LocalTime end){
        LocalTime currentTime = showing.getStartTime().toLocalTime();
        return currentTime.isAfter(start) && currentTime.isBefore(end);
    }

    private static double getSequenceDiscount(Showing showing) {
        switch (showing.getSequenceOfTheDay()) {
            case 1: return 3;
            case 2: return 2;
            case 7:
                return 1;
        }
        return 0;
    }

    private static double getSpecialDiscount(Showing showing){
        if(SPECIAL_MOVIE_CODE == showing.getMovie().getSpecialCode()){
            return showing.getMovie().getTicketPrice() * SPECIAL_DISCOUNT;
        }
        return 0;
    }

    private static double getTimeDiscount(Showing showing){
        if(isBetweenHours(showing,LocalTime.of(11,0), LocalTime.of(16,0))){
            return showing.getMovie().getTicketPrice() * TIME_DISCOUNT;
        }
        return 0;
    }
    public static double calculateTotalFee(Showing showing, int audienceCount) {
        double originalPrice = showing.getMovie().getTicketPrice();
        double discount  = max(getSequenceDiscount(showing) ,max(getSpecialDiscount(showing), getTimeDiscount(showing)));
        return max(0, (originalPrice - discount) * audienceCount);
    }

}
