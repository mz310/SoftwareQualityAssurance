package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class PersonTest {

    @Test
    public void testAddMeeting_setsBusy() throws Exception {
        Person p = new Person("Dana");
        Room r = new Room("2A01");
        Meeting m = new Meeting(6, 20, 9, 10, new ArrayList<>(), r, "One-on-one");
        p.addMeeting(m);
        assertTrue(p.isBusy(6, 20, 9, 10));
    }

    @Test
    public void testAddMeeting_conflictWrappedMessage() throws Exception {
        Person p = new Person("Evan");
        Room r = new Room("2A02");
        p.addMeeting(new Meeting(6, 21, 10, 12, new ArrayList<>(), r, "Sprint planning"));
        try {
            p.addMeeting(new Meeting(6, 21, 11, 13, new ArrayList<>(), r, "Retro"));
            fail("Expected TimeConflictException");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for attendee Evan"));
        }
    }

    @Test
    public void testPrintAgenda_containsDescription() throws Exception {
        Person p = new Person("Fiona");
        Room r = new Room("2A03");
        p.addMeeting(new Meeting(6, 5, 14, 16, new ArrayList<>(), r, "Interview"));
        String agenda = p.printAgenda(6, 5);
        assertTrue(agenda.contains("Interview"));
    }
}
