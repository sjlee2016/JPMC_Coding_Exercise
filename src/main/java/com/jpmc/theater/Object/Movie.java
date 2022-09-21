package com.jpmc.theater.Object;

import org.json.JSONObject;

import java.time.Duration;
import java.util.Objects;

public class Movie {

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
        this.description = "";
    }

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode, String description) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getSpecialCode() { return specialCode;}

    public String getDescription() { return description;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }
    @Override
    public String toString(){
        return title + " " + "(" + runningTime + ")";
    }

    public JSONObject toJSONObject(){
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("running_time", runningTime);
        json.put("ticket_price", ticketPrice);
        json.put("special_code", specialCode);
        return json;
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}