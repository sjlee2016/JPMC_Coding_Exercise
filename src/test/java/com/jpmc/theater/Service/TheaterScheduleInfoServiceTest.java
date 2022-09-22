package com.jpmc.theater.Service;

import com.jpmc.theater.Object.LocalDateProvider;
import com.jpmc.theater.Object.Movie;
import com.jpmc.theater.Object.Showing;
import com.jpmc.theater.Object.Theater;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterScheduleInfoServiceTest {
    TheaterScheduleInfoService theaterScheduleInfoService;

    public void setTestData(){
        LocalDateProvider provider = LocalDateProvider.singleton(LocalDate.of(2022,9,21));
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        List<Showing> schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(provider.getCurrentDate(), LocalTime.of(23, 0)))
        );
        Theater theater = new Theater(LocalDateProvider.singleton(), schedule);
        theaterScheduleInfoService = new TheaterScheduleInfoService(theater);
    }

    @Test
    void testConvertScheduleDetailsToString() {
        setTestData();
        String expected = "2022-09-21\n===================================================\n1: 2022-09-21T09:00 Turning Red (1 hour 25 minutes) $11.0\n2: 2022-09-21T11:00 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n3: 2022-09-21T12:50 The Batman (1 hour 35 minutes) $9.0\n4: 2022-09-21T14:30 Turning Red (1 hour 25 minutes) $11.0\n5: 2022-09-21T16:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n6: 2022-09-21T17:50 The Batman (1 hour 35 minutes) $9.0\n7: 2022-09-21T19:30 Turning Red (1 hour 25 minutes) $11.0\n8: 2022-09-21T21:10 Spider-Man: No Way Home (1 hour 30 minutes) $12.5\n9: 2022-09-21T23:00 The Batman (1 hour 35 minutes) $9.0\n===================================================\n";
        assertEquals(expected, theaterScheduleInfoService.getScheduleDetails());
        System.out.println(theaterScheduleInfoService.getScheduleDetails());
    }

    @Test
    void testConvertScheduleDetailToJSON(){
        setTestData();
        JSONObject json = theaterScheduleInfoService.getScheduleDetailsInJSON();
        assertEquals(9, json.length());
        JSONObject json2 = (JSONObject) json.get("0");
        JSONObject json3 = (JSONObject) json2.get("movie");
        assertEquals("2022-09-21T09:00", json2.get("show_start_time").toString());
        assertEquals(11.0, json3.get("ticket_price"));
        assertEquals("Turning Red", json3.get("title"));
        assertEquals(0, json3.get("special_code"));
        assertEquals("PT1H25M", json3.get("running_time").toString());
        assertEquals("", json3.get("description"));
        System.out.println(json);
    }
}
