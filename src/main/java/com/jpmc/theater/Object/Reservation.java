package com.jpmc.theater.Object;
import com.jpmc.theater.Utilities.TicketPriceUtils;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    private double totalFee;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
        this.totalFee = TicketPriceUtils.calculateTotalFee(showing, audienceCount);
    }

    public int getAudienceCount(){
        return audienceCount;
    }
    public double getTotalFee(){
        return totalFee;
    }

    public Showing getShowing(){
        return showing;
    }
    public Customer getCustomer(){
        return customer;
    }

}