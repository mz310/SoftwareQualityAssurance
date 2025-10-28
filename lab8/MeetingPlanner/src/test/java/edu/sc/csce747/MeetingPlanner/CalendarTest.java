package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalendarTest {

    @Test
    public void testAddMeeting_holiday() {
        Calendar calendar = new Calendar();
        try {
            Meeting midsommar = new Meeting(6, 26, "Midsommar");
            calendar.addMeeting(midsommar);
            boolean added = calendar.isBusy(6, 26, 0, 23);
            assertTrue("Midsommar should be marked as busy on the calendar", added);
        } catch (TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testHappyPath_AddAndQuery() throws Exception {
        Calendar calendar = new Calendar();
        calendar.addMeeting(new Meeting(6, 1, 9, 11));
        assertTrue(calendar.isBusy(6, 1, 9, 10));
        assertTrue(calendar.isBusy(6, 1, 10, 11));
        assertFalse(calendar.isBusy(6, 1, 11, 12));
    }

    @Test
    public void testInvalidMonthZero_checkTimes() {
        try {
            Calendar.checkTimes(0, 10, 9, 10);
            fail("Expected TimeConflictException for month 0");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testInvalidMonthThirteen_checkTimes() {
        try {
            Calendar.checkTimes(13, 10, 9, 10);
            fail("Expected TimeConflictException for month 13");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testInvalidDayThirtyTwo_checkTimes() {
        try {
            Calendar.checkTimes(6, 32, 9, 10);
            fail("Expected TimeConflictException for day 32");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testInvalidTimeRange_StartEqualsEnd_checkTimes() {
        try {
            Calendar.checkTimes(6, 10, 10, 10);
            fail("Expected TimeConflictException for start>=end");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testOverlapWrapped_ShouldThrow() throws Exception {
        Calendar calendar = new Calendar();
        calendar.addMeeting(new Meeting(6, 10, 10, 12));
        try {
            // new meeting fully wraps the existing one -> should conflict
            calendar.addMeeting(new Meeting(6, 10, 9, 13));
            fail("Expected TimeConflictException for wrapped overlap");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testNonExistentDay_Feb30_ShouldThrow() {
        Calendar calendar = new Calendar();
        try {
            calendar.addMeeting(new Meeting(2, 30, 9, 10));
            // If code allows this, it is a defect.
            fail("Expected TimeConflictException for non-existent day 2/30");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testDecemberIsValid() {
        Calendar calendar = new Calendar();
        try {
            calendar.addMeeting(new Meeting(12, 1, 9, 10));
            assertTrue(calendar.isBusy(12, 1, 9, 10));
        } catch (TimeConflictException e) {
            fail("December should be valid month. Check month validation (>=12). " + e.getMessage());
        }
    }
}
