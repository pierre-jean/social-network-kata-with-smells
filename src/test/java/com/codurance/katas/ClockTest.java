package com.codurance.katas;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ClockTest {

    @Test
    public void should_return_5_sec() {
        Calendar calendar = Calendar.getInstance();
        Date t1 = calendar.getTime();
        calendar.add(Calendar.SECOND, 5);
        Date t2 = calendar.getTime();

       Clock clock = new Clock();

        String result = clock.getDelay(t1, t2);

        assertThat(result, is(equalTo("5 sec")));
    }

    @Test
    public void should_return_5_min() {
        Calendar calendar = Calendar.getInstance();
        Date t1 = calendar.getTime();
        calendar.add(Calendar.MINUTE, 5);
        Date t2 = calendar.getTime();

        Clock clock = new Clock();

        String result = clock.getDelay(t1, t2);

        assertThat(result, is(equalTo("5 min")));
    }
}