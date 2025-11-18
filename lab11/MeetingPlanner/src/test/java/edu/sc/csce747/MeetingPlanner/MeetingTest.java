package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class MeetingTest {

    // Whole day meeting with only description → must not crash
    @Test
    public void testToString_NoError_WhenHolidayOnly() {
        Meeting m = new Meeting(6, 15, "Holiday");
        String s = m.toString();
        assertNotNull(s);
        assertTrue(s.contains("6/15"));
        assertTrue(s.contains("Holiday"));
    }

    // Meeting with full data → must include attendees + room information
    @Test
    public void testToString_ShowsRoom_AndAttendees() {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Alice"));
        people.add(new Person("Bob"));
        Room room = new Room("2A01");

        Meeting m = new Meeting(6, 15, 10, 12, people, room, "Design review");

        String s = m.toString();
        assertTrue(s.contains("2A01"));
        assertTrue(s.contains("Design review"));
        assertTrue(s.contains("Alice"));
        assertTrue(s.contains("Bob"));
    }

    // Add attendee and remove attendee behavior test
    @Test
    public void testAddAndRemoveAttendee() {
        Meeting m = new Meeting(6, 20, 9, 11);
        Person p = new Person("John");

        m.addAttendee(p);
        assertEquals(1, m.getAttendees().size());

        m.removeAttendee(p);
        assertEquals(0, m.getAttendees().size());
    }

    // Time constructor only (no room/desc) must still be printable
    @Test
    public void testToString_TimeOnlyMeeting() {
        Meeting m = new Meeting(7, 3, 14, 16);
        String out = m.toString();
        assertNotNull(out);
        assertTrue(out.contains("7/3"));
        assertTrue(out.contains("14"));
        assertTrue(out.contains("16"));
    }
}
