package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class MeetingTest {

    @Test
    public void testToString_NoNPE_WhenNoAttendeesOrRoom() {
        Meeting m = new Meeting(6, 15, "Holiday");
        String s = m.toString();
        assertNotNull(s);
        assertTrue(s.contains("6/15"));
    }

    @Test
    public void testToString_WithAttendeesAndRoom() {
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("Alice"));
        attendees.add(new Person("Bob"));
        Room room = new Room("2A01");
        Meeting m = new Meeting(6, 15, 10, 12, attendees, room, "Design review");
        String s = m.toString();
        assertTrue(s.contains("2A01"));
        assertTrue(s.contains("Design review"));
        assertTrue(s.contains("Alice"));
        assertTrue(s.contains("Bob"));
    }
}
