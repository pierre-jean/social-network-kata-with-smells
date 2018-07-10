package com.codurance.katas;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.internal.verification.InOrderWrapper;
import org.mockito.verification.VerificationMode;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SocialNetworkShould {

    @Test
    public void display_timeline() {
        Console console = mock(Console.class);
        when(console.readLineFromInput()).thenReturn(
                "Alice -> I love the weather today",
                "Bob -> Damn we lost",
                "Bob -> Good game though.",
                "Alice",
                "exit"
        );
        Clock clock = mock(Clock.class);
        when(clock.getDelay(any(), any())).thenReturn("5 min");
        when(clock.timeElapsed(any(Date.class), any(Date.class))).thenReturn("5 min");

        SocialNetwork socialNetwork = new SocialNetwork(console, clock);

        socialNetwork.execute();

        verify(console).writeLineToOutput("I love the weather today (5 min ago)");
    }

    @Test
    public void display_timeline_2() {
        Console console = mock(Console.class);


        when(console.readLineFromInput()).thenReturn(
                "Alice -> I love the weather today",
                "Bob -> Damn we lost",
                "Bob -> Good game though.",
                "Bob",
                "exit"
        );
        Clock clock = mock(Clock.class);
        when(clock.getDelay(any(), any())).thenReturn("1 min", "5 min");

        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        Date time1 = calendar.getTime();
        calendar.add(Calendar.SECOND, 5);
        Date time2 = calendar.getTime();
        calendar.add(Calendar.MINUTE, 5);
        Date time3 = calendar.getTime();
        when(clock.now()).thenReturn(time1, time2, time3);

        SocialNetwork socialNetwork = new SocialNetwork(console, clock);

        socialNetwork.execute();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).writeLineToOutput("Good game though. (1 min ago)");
        inOrder.verify(console).writeLineToOutput("Damn we lost (5 min ago)");
    }

    @Test
    public void display_timeline_3() {
        Console console = mock(Console.class);
        when(console.readLineFromInput()).thenReturn(
                "Alice -> I love the weather today",
                "Alice -> Another sentence",
                "Alice",
                "exit"
        );
        Clock clock = mock(Clock.class);
        when(clock.getDelay(any(), any())).thenReturn("15 sec", "1 min");
        when(clock.timeElapsed(any(Date.class), any(Date.class))).thenReturn("5 min");

        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        Date time1 = calendar.getTime();
        calendar.add(Calendar.SECOND, 5);
        Date time2 = calendar.getTime();
        calendar.add(Calendar.MINUTE, 5);
        Date time3 = calendar.getTime();
        when(clock.now()).thenReturn(time1, time2, time3);

        SocialNetwork socialNetwork = new SocialNetwork(console, clock);

        socialNetwork.execute();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).writeLineToOutput("Another sentence (15 sec ago)");
        inOrder.verify(console).writeLineToOutput("I love the weather today (1 min ago)");
    }

    @Test
    public void should_follow() {
        Console console = mock(Console.class);
        when(console.readLineFromInput()).thenReturn(
                "Alice -> I love the weather today",
                "Bob -> Hello",
                "Alice -> Hello again",
                "Alice follows Bob",
                "Alice wall",
                "exit"
        );

        Clock clock = mock(Clock.class);
        when(clock.getDelay(any(), any())).thenReturn("15 sec", "1 min", "10 min");
        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        Date time1 = calendar.getTime();
        calendar.add(Calendar.SECOND, 5);
        Date time2 = calendar.getTime();
        calendar.add(Calendar.MINUTE, 5);
        Date time3 = calendar.getTime();
        when(clock.now()).thenReturn(time1, time2, time3);
        when(clock.timeElapsed(any(Date.class), any(Date.class))).thenReturn("5 min");

        SocialNetwork socialNetwork = new SocialNetwork(console, clock);

        socialNetwork.execute();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).writeLineToOutput("Alice - Hello again (15 sec ago)");
        inOrder.verify(console).writeLineToOutput("Bob - Hello (1 min ago)");
        inOrder.verify(console).writeLineToOutput("Alice - I love the weather today (10 min ago)");
    }
}
