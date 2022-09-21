package com.jpmc.theater.Object;
import com.jpmc.theater.Utilities.TicketPriceUtils;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public double getTotalFee(){
        return TicketPriceUtils.calculateTotalFee(showing, audienceCount);
    }

}