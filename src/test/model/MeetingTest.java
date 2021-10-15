package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MeetingTest {

    private Meeting meeting;

    @BeforeEach
    public void runBefore() {
        meeting = new Meeting(2, 8, 10, 2, 2, 20);
    }

    @Test
    public void testMeeting() {
        assertEquals(2, meeting.getMeetingID());
        assertFalse(meeting.getIsCompleted());
        assertEquals(2, meeting.getDuration());
    }

    @Test
    public void testGetMeetingID() {
        assertEquals(2, meeting.getMeetingID());
        meeting.setMeetingID(5);
        assertEquals(5, meeting.getMeetingID());
        Meeting meeting1 = new Meeting(3, 11, 10, 10, 3, 5);
        assertEquals(3, meeting1.getMeetingID());
    }

    @Test
    public void testGetRoomNo() {
        assertEquals(20, meeting.getRoomNO());
        meeting.setRoomNO(13);
        assertEquals(13, meeting.getRoomNO());
        Meeting meeting1 = new Meeting(3, 11, 10, 10, 3, 5);
        assertEquals(5, meeting1.getRoomNO());
    }

    @Test
    public void testGetMonth() {
        assertEquals(8, meeting.getMonth());
        meeting.setMonth(12);
        assertEquals(12, meeting.getMonth());
        Meeting meeting1 = new Meeting(3, 11, 10, 10, 3, 5);
        assertEquals(11, meeting1.getMonth());
    }

    @Test
    public void testGetDay() {
        assertEquals(10, meeting.getDay());
        meeting.setDay(25);
        assertEquals(25, meeting.getDay());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 3, 5);
        assertEquals(14, meeting1.getDay());
    }

    @Test
    public void testGetFromHour() {
        assertEquals(2, meeting.getFromHour());
        meeting.setFromHour(9);
        assertEquals(9, meeting.getFromHour());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 3, 5);
        assertEquals(10, meeting1.getFromHour());
    }

    @Test
    public void testGetDuration() {
        assertEquals(2, meeting.getDuration());
        meeting.setDuration(3);
        assertEquals(3, meeting.getDuration());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 5, 5);
        assertEquals(5, meeting1.getDuration());
    }

    public void testGetIsCompleted() {
        assertFalse(meeting.getIsCompleted());
        meeting.setCompleted();
        assertTrue(meeting.getIsCompleted());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 5, 5);
        assertFalse(meeting1.getIsCompleted());
    }


    public void testSetCompleted() {
        assertFalse(meeting.getIsCompleted());
        meeting.setCompleted();
        assertTrue(meeting.getIsCompleted());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 5, 5);
        assertFalse(meeting1.getIsCompleted());
        meeting1.setCompleted();
        assertTrue(meeting1.getIsCompleted());
    }


    @Test
    public void testIsSameMeeting() {
        Meeting meeting1 = new Meeting(1, 8, 10, 2, 2, 20);
        Meeting meeting2 = new Meeting(3, 30, 20, 12, 1, 5);
        assertTrue(meeting.isSameMeeting(meeting1));
        assertFalse(meeting.isSameMeeting(meeting2));
    }

    @Test
    public void testSetMeetingID() {
        assertEquals(2, meeting.getMeetingID());
        meeting.setMeetingID(16);
        assertEquals(16, meeting.getMeetingID());
    }

    @Test
    public void testSetMonth() {
        assertEquals(8, meeting.getMonth());
        meeting.setMonth(12);
        assertEquals(12, meeting.getMonth());
    }

    @Test
    public void testSetDay() {
        assertEquals(10, meeting.getDay());
        meeting.setDay(28);
        assertEquals(28, meeting.getDay());
    }

    @Test
    public void testSetFromHour() {
        assertEquals(2, meeting.getFromHour());
        meeting.setFromHour(9);
        assertEquals(9, meeting.getFromHour());
    }

    @Test
    public void testSetDuration() {
        assertEquals(2, meeting.getDuration());
        meeting.setDuration(4);
        assertEquals(4, meeting.getDuration());
    }

    @Test
    public void testSetRoomNO() {
        assertEquals(20, meeting.getRoomNO());
        meeting.setRoomNO(17);
        assertEquals(17, meeting.getRoomNO());
    }





















}
