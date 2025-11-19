package edu.sc.csce747.MeetingPlanner;

import java.util.ArrayList;

public class Meeting {
    private int month;
    private int day;
    private int start;
    private int end;
    private ArrayList<Person> attendees;
    private Room room;
    private String description;

    /**
     * Default constructor
     */
    public Meeting() {
        this.month = 0;
        this.day = 0;
        this.start = 0;
        this.end = 0;
        this.description = "";
        this.room = null;
        this.attendees = new ArrayList<>();   // ЭНД ЗААВАЛ ИНИЦИАЛИЗ ХИЙНЭ
    }

    /**
     * Constructor that can be used to block off a whole day -
     * such as for a vacation
     */
    public Meeting(int month, int day){
        this();               // default constructor-оо дуудна
        this.month = month;
        this.day = day;
        this.start = 0;
        this.end = 23;
    }

    /**
     * Constructor that can be used to block off a whole day -
     * such as for a vacation
     */
    public Meeting(int month, int day, String description){
        this(month, day);     // дээрхийнээ дуудна
        this.description = description;
    }

    /**
     * More detailed constructor
     */
    public Meeting(int month, int day, int start, int end){
        this();               // attendees-г null биш болгоно
        this.month = month;
        this.day = day;
        this.start = start;
        this.end = end + 1;
    }

    /**
     * More detailed constructor
     */
    public Meeting(int month, int day, int start, int end,
                   ArrayList<Person> attendees, Room room, String description){
        this(month, day, start, end);   // дээрхийнээ дуудна
        this.room = room;
        this.description = description;
        this.attendees = (attendees == null ? new ArrayList<>() : attendees);
    }

    /**
     * Add an attendee to the meeting.
     */
    public void addAttendee(Person attendee) {
        if (attendee == null) return;
        this.attendees.add(attendee);
    }

    /**
     * Removes an attendee from the meeting.
     */
    public void removeAttendee(Person attendee) {
        if (attendees == null) return;  // хамгаалалт, гэхдээ одоо бол null биш байх ёстой
        this.attendees.remove(attendee);
    }

    /**
     * Returns information about the meeting as a formatted string.
     */
    @Override
    public String toString() {
        String roomPart = (room == null) ? "(no-room)" : room.getID();
        String descPart = (description == null) ? "(no-description)" : description;

        StringBuilder info = new StringBuilder();
        info.append(month).append("/").append(day)
                .append(", ").append(start).append(" - ").append(end)
                .append(",").append(roomPart).append(": ").append(descPart)
                .append("\nAttending: ");

        if (attendees == null || attendees.isEmpty()) {
            info.append("(none)");
        } else {
            for (Person attendee : attendees) {
                info.append(attendee.getName()).append(",");
            }
            // төгсгөлийн илүү таслалыг арилгана
            info.setLength(info.length() - 1);
        }

        return info.toString();
    }

    // Getters / setters...

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }

    public int getStartTime() { return start; }
    public void setStartTime(int start) { this.start = start; }

    public int getEndTime() { return end; }
    public void setEndTime(int end) { this.end = end; }

    public ArrayList<Person> getAttendees() { return attendees; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
