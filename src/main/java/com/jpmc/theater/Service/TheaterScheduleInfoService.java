package com.jpmc.theater.Service;

import com.jpmc.theater.Object.Showing;
import com.jpmc.theater.Object.Theater;
import org.json.JSONObject;

public class TheaterScheduleInfoService {
    private Theater theater;
    private ReservationService reservationService;
    public TheaterScheduleInfoService(Theater theater) {
        this.theater = theater;
    }
    public String getScheduleDetails(){
            StringBuilder str = new StringBuilder();
            for(Showing s : theater.getSchedule()){
                str.append(s+"\n");
            }
            return str.toString();
    }

    public JSONObject getScheduleDetailsInJSON(){
        JSONObject json=new JSONObject();
            int sequence = 0;
            for(Showing s : theater.getSchedule()){
                json.put(String.valueOf(sequence++), s.toJSONObject());
            }
            return json;
    }
    public void printTheaterSchedule(){
        System.out.println(theater.getProvider().getCurrentDate());
        System.out.println("===================================================");
        System.out.println(getScheduleDetails());
        System.out.println("===================================================");
    }

}
