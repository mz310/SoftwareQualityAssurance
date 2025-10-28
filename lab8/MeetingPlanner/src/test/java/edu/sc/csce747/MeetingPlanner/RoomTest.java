package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class RoomTest {

    @Test
    public void testAddMeeting_setsBusy() throws Exception {
        Room r = new Room("2A05");
        Meeting m = new Meeting(6, 10, 8, 9, new ArrayList<>(), r, "Daily");
        r.addMeeting(m);
        assertTrue(r.isBusy(6, 10, 8, 9));
    }

    @Test
    public void testAddMeeting_conflictWrappedMessage() throws Exception {
        Room r = new Room("2A04");
        r.addMeeting(new Meeting(6, 11, 10, 12, new ArrayList<>(), r, "Lecture"));
        try {
            r.addMeeting(new Meeting(6, 11, 11, 13, new ArrayList<>(), r, "Overlap"));
            fail("Expected TimeConflictException");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for room 2A04"));
        }
    }

    @Test
    public void testPrintAgenda_containsDescription() throws Exception {
        Room r = new Room("2A03");
        r.addMeeting(new Meeting(6, 9, 15, 16, new ArrayList<>(), r, "Defense"));
        String agenda = r.printAgenda(6, 9);
        assertTrue(agenda.contains("Defense"));
    }
}
