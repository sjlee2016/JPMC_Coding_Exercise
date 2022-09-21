package com.jpmc.theater.Object;

import java.time.LocalDate;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;
    private static LocalDate currentDate;
    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
            currentDate = LocalDate.now();
        }
            return instance;
        }
    public static LocalDateProvider singleton(LocalDate date) {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
        currentDate = date;
        return instance;
    }

    public LocalDate getCurrentDate() {
            return currentDate;
    }
}
