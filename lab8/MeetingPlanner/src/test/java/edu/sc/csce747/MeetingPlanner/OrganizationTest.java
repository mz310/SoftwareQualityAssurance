package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrganizationTest {

    @Test
    public void testGetRoom_valid() throws Exception {
        Organization org = new Organization();
        Room r = org.getRoom("2A01");
        assertEquals("2A01", r.getID());
    }

    @Test
    public void testGetRoom_invalid_throws() {
        Organization org = new Organization();
        try {
            org.getRoom("X999");
            fail("Should throw for invalid room");
        } catch (Exception expected) {}
    }

    @Test
    public void testGetEmployee_valid() throws Exception {
        Organization org = new Organization();
        Person p = org.getEmployee("Greg Gay");
        assertEquals("Greg Gay", p.getName());
    }

    @Test
    public void testGetEmployee_invalid_throws() {
        Organization org = new Organization();
        try {
            org.getEmployee("Unknown Person");
            fail("Should throw for invalid employee");
        } catch (Exception expected) {}
    }
}
