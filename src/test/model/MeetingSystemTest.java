package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeetingSystemTest {

    private MeetingSystem meetingList;

    @BeforeEach
    public void runBefore() {
        meetingList = new MeetingSystem();
    }

    @Test
    public void testMeetingSystem() {
        assertEquals(0, meetingList.length());
    }

    @Test
    public void testAddMeeting() {

        Meeting meeting = new Meeting(1, 10, 8, 13, 2, 15);
        assertEquals("Your meeting has been added!", meetingList.addMeeting(meeting));
        assertEquals(1, meetingList.length());
        assertEquals(10, meetingList.getMonth(1));
        assertEquals(8, meetingList.getDay(1));
        meetingList.addMeeting(meeting);
        assertEquals(1, meetingList.length());
        Meeting meeting1 = new Meeting(10, 10, 1, 10, 2, 20);
        meetingList.addMeeting(meeting1);
        assertEquals(2, meetingList.length());
        Meeting meeting2 = new Meeting(10, 10, 1, 10, 2, 25);
        meetingList.addMeeting(meeting2);
        assertEquals(2, meetingList.length());


    }


    @Test
    public void testGetMeetingPlace() {
        assertEquals(-1, meetingList.getMeetingPlace(2));
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 10, 1, 10, 2, 20);
        meetingList.addMeeting(meeting1);
        assertEquals(20, meetingList.getMeetingPlace(10));
        assertEquals(-1, meetingList.getMeetingPlace(7));
    }

    @Test
    public void testGetMonth() {
        assertEquals(-1, meetingList.getMonth(2));
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 2, 20);
        meetingList.addMeeting(meeting1);
        assertEquals(2, meetingList.length());
        assertEquals(5, meetingList.getMonth(10));
        assertEquals(-1, meetingList.getMonth(6));
    }

    @Test
    public void testGetDay() {
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 2, 20);
        meetingList.addMeeting(meeting1);
        assertEquals(1, meetingList.getDay(10));
        assertEquals(-1, meetingList.getDay(5));
    }

    @Test
    public void testGetFromHour() {
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 1, 20);
        meetingList.addMeeting(meeting1);
        assertEquals(10, meetingList.getFromHour(10));
        assertEquals(-1, meetingList.getFromHour(7));
    }

    @Test
    void testLength() {
        assertEquals(0, meetingList.length());
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 1, 20);
        meetingList.addMeeting(meeting1);
        assertEquals(2, meetingList.length());
    }

    @Test
    public void testCancelMeeting() {
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 1, 20);
        meetingList.addMeeting(meeting1);
        assertEquals(true, meetingList.cancelMeeting(10));
        assertEquals(1, meetingList.length());
        assertEquals(false, meetingList.cancelMeeting(4));
        assertEquals(true, meetingList.cancelMeeting(3));
        assertEquals(0, meetingList.length());
    }

}