package com.jpmc.theater.Service;

import com.jpmc.theater.Object.Customer;
import com.jpmc.theater.Object.Reservation;
import com.jpmc.theater.Object.Showing;
import com.jpmc.theater.Object.Theater;
import scala.xml.Null;

public class ReservationService {
    Theater theater;
    public ReservationService(Theater theater){
        this.theater = theater;
    }

    public Reservation reserve(Customer customer, int sequence, int audienceCount) {
        Showing showing;
        if(audienceCount <= 0){
            throw new IllegalArgumentException("audience count out of range");
        }
        if(sequence <= 0){
            throw new IllegalArgumentException("sequence out of range");
        }
        try {
            showing = theater.getSchedule().get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, audienceCount);
    }

}
