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
    public void testIsSameMeeting() {
        Meeting meeting1 = new Meeting(1, 8, 10, 2, 2, 20);
        Meeting meeting2 = new Meeting(3, 30, 20, 12, 1, 5);
        assertTrue(meeting.isSameMeeting(meeting1));
        assertFalse(meeting.isSameMeeting(meeting2));
    }

    @Test
    public void testGetRoomNO() {
        assertEquals(20, meeting.getRoomNO());
    }



}
