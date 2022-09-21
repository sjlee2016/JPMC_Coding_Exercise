package com.jpmc.theater.Object;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;
public class Theater {

    private LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider, List<Showing> schedule) {
        this.provider = provider;
        this.schedule = schedule;
    }

    public List<Showing> getSchedule(){
        return schedule;
    }

    public LocalDateProvider getProvider(){
        return provider;
    }

}
