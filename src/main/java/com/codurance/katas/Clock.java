package com.codurance.katas;

import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Clock {

    public String timeElapsed(Date first, Date last) {
        Period p = Period.between(first.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), last.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        if (ChronoUnit.DAYS.between(first.toInstant(), last.toInstant()) > 1){
            return String.format("%d days", ChronoUnit.DAYS.between(first.toInstant(), last.toInstant()));
        }
        if (ChronoUnit.HOURS.between(first.toInstant(), last.toInstant()) >1){
            return String.format("%d hours", ChronoUnit.HOURS.between(first.toInstant(), last.toInstant()));
        }
        if (ChronoUnit.MINUTES.between(first.toInstant(), last.toInstant()) >1){
            return String.format("%d min", ChronoUnit.MINUTES.between(first.toInstant(), last.toInstant()));
        }
        return String.format("%d sec", ChronoUnit.SECONDS.between(first.toInstant(), last.toInstant()));
    }

    public String getDelay(Date firstDate, Date lastDate) {
        return timeElapsed(firstDate, lastDate);
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
}
