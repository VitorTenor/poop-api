package com.vitortenorio.poop_api.enums;

import java.time.LocalDateTime;

public enum DayPeriod {
    NIGHT("00:00", "05:59"),
    MORNING("06:00", "11:59"),
    AFTERNOON("12:00", "17:59"),
    EVENING("18:00", "23:59");

    private final String startTime;
    private final String endTime;

    DayPeriod(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public static DayPeriod classify(LocalDateTime dateTime) {
        int hour = dateTime.getHour();

        if (hour >= 0 && hour <= 5) {
            return NIGHT;
        } else if (hour >= 6 && hour <= 11) {
            return MORNING;
        } else if (hour >= 12 && hour <= 17) {
            return AFTERNOON;
        } else {
            return EVENING;
        }
    }
}
