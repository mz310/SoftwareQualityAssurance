package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalendarTest {
    
    @Test
    public void testAddHolidayMarksBusy() {
        Calendar calendar = new Calendar();
        try {
            Meeting midsommar = new Meeting(6, 26, "Midsommar");
            calendar.addMeeting(midsommar);
            boolean busy = calendar.isBusy(6, 26, 0, 23);
            assertTrue("Holiday should mark the whole day as busy", busy);
        } catch (TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testAddMeetingAndCheckBusyTimes() throws Exception {
        Calendar calendar = new Calendar();
        calendar.addMeeting(new Meeting(6, 1, 9, 11));
        assertTrue(calendar.isBusy(6, 1, 9, 10));   // дотор нь
        assertTrue(calendar.isBusy(6, 1, 10, 11));  // дотор нь
        assertFalse(calendar.isBusy(6, 1, 11, 12)); // гадуур нь
    }

    @Test
    public void testIsBusyWhenFree() throws Exception {
        Calendar calendar = new Calendar();
        assertFalse(calendar.isBusy(7, 7, 14, 15));
    }
    @Test
    public void testAddMeetingSuccess() throws Exception {
        Calendar calendar = new Calendar();
        calendar.addMeeting(new Meeting(7, 7, 14, 15));
        assertTrue(calendar.isBusy(7, 7, 14, 15));
    }

    @Test
    public void testInvalidMonthDayAndTimeThrowException() {
        // month = 0
        try {
            Calendar.checkTimes(0, 10, 9, 10);
            fail("Expected TimeConflictException for month 0");
        } catch (TimeConflictException expected) {}

        // month = 13
        try {
            Calendar.checkTimes(13, 10, 9, 10);
            fail("Expected TimeConflictException for month 13");
        } catch (TimeConflictException expected) {}

        // day = 32
        try {
            Calendar.checkTimes(6, 32, 9, 10);
            fail("Expected TimeConflictException for day 32");
        } catch (TimeConflictException expected) {}

        // start == end
        try {
            Calendar.checkTimes(6, 10, 10, 10);
            fail("Expected TimeConflictException for start >= end");
        } catch (TimeConflictException expected) {}
    }
    @Test
    public void testOverlappingMeetingsNotAllowed() throws Exception {
        Calendar calendar = new Calendar();
        // 10–12 уулзалт байна
        calendar.addMeeting(new Meeting(6, 10, 10, 12));

        try {
            // 9–13 уулзалт нь өмнөхийг бүхлээр нь давхцуулна
            calendar.addMeeting(new Meeting(6, 10, 9, 13));
            fail("Expected TimeConflictException for overlapping meetings");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testNonexistentDateNotAllowed() {
        Calendar calendar = new Calendar();
        try {
            calendar.addMeeting(new Meeting(2, 30, 9, 10));
            fail("Expected TimeConflictException for non-existent date 2/30");
        } catch (TimeConflictException expected) {}
    }

    @Test
    public void testDecemberIsValidMonth() {
        Calendar calendar = new Calendar();
        try {
            calendar.addMeeting(new Meeting(12, 1, 9, 10));
            assertTrue(calendar.isBusy(12, 1, 9, 10));
        } catch (TimeConflictException e) {
            fail("December should be a valid month. Check month validation: " + e.getMessage());
        }
    }
    @Test
    public void testClearScheduleRemovesAllMeetings() throws Exception {
        Calendar calendar = new Calendar();
        calendar.addMeeting(new Meeting(8, 2, 8, 9));
        assertTrue(calendar.isBusy(8, 2, 8, 9));

        calendar.clearSchedule(8, 2);
        assertFalse(calendar.isBusy(8, 2, 8, 9));
    }
    @Test
    public void testPrintAgendaForMonthAndDay() throws Exception {
        Calendar calendar = new Calendar();
        Meeting m = new Meeting(8, 3, 10, 11, null, null, "Standup");
        calendar.addMeeting(m);

        String monthAgenda = calendar.printAgenda(8);
        String dayAgenda = calendar.printAgenda(8, 3);

        assertTrue(monthAgenda.contains("Standup"));
        assertTrue(dayAgenda.contains("Standup"));
    }
    @Test
    public void testGetAndRemoveMeetingByIndex() throws Exception {
        Calendar calendar = new Calendar();
        calendar.addMeeting(new Meeting(8, 4, 9, 10, null, null, "1:1"));
        calendar.addMeeting(new Meeting(8, 4, 11, 12, null, null, "Review"));

        Meeting first = calendar.getMeeting(8, 4, 0);
        assertEquals("1:1", first.getDescription());

        // Эхний уулзалтыг устгаад тухайн цаг чөлөөтэй болсныг шалгах
        calendar.removeMeeting(8, 4, 0);
        assertFalse(calendar.isBusy(8, 4, 9, 10));

        // Харин 11–12 хэвээрээ завгүй
        assertTrue(calendar.isBusy(8, 4, 11, 12));
    }
}
