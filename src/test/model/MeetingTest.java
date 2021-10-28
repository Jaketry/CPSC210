package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeetingTest {

    private Meeting meeting;

    @BeforeEach
    public void runBefore() {
        meeting = new Meeting(2, 8, 10, 2, 2, 20, false);
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
        Meeting meeting1 = new Meeting(3, 11, 10, 10, 3, 5, false);
        assertEquals(3, meeting1.getMeetingID());
    }

    @Test
    public void testGetRoomNo() {
        assertEquals(20, meeting.getRoomNO());
        meeting.setRoomNO(13);
        assertEquals(13, meeting.getRoomNO());
        Meeting meeting1 = new Meeting(3, 11, 10, 10, 3, 5, false);
        assertEquals(5, meeting1.getRoomNO());
    }

    @Test
    public void testGetMonth() {
        assertEquals(8, meeting.getMonth());
        meeting.setMonth(12);
        assertEquals(12, meeting.getMonth());
        Meeting meeting1 = new Meeting(3, 11, 10, 10, 3, 5, false);
        assertEquals(11, meeting1.getMonth());
    }

    @Test
    public void testGetDay() {
        assertEquals(10, meeting.getDay());
        meeting.setDay(25);
        assertEquals(25, meeting.getDay());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 3, 5, false);
        assertEquals(14, meeting1.getDay());
    }

    @Test
    public void testGetFromHour() {
        assertEquals(2, meeting.getFromHour());
        meeting.setFromHour(9);
        assertEquals(9, meeting.getFromHour());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 3, 5, false);
        assertEquals(10, meeting1.getFromHour());
    }

    @Test
    public void testGetDuration() {
        assertEquals(2, meeting.getDuration());
        meeting.setDuration(3);
        assertEquals(3, meeting.getDuration());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 5, 5, false);
        assertEquals(5, meeting1.getDuration());
    }

    @Test
    public void testGetIsCompleted() {
        assertFalse(meeting.getIsCompleted());
        meeting.setCompleted();
        assertTrue(meeting.getIsCompleted());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 5, 5, false);
        assertFalse(meeting1.getIsCompleted());
    }





    @Test
    public void testIsSameMeeting() {
        Meeting meeting1 = new Meeting(1, 8, 10, 2, 2, 20, false);
        Meeting meeting2 = new Meeting(3, 7, 20, 12, 1, 5, false);
        Meeting meeting3 = new Meeting(3, 9, 20, 12, 1, 20, false);
        Meeting meeting4 = new Meeting(9, 9, 21, 11, 3, 20, false);
        Meeting meeting5 = new Meeting(23, 9, 21, 10, 3, 20, false);
        Meeting meeting6 = new Meeting(21, 9, 21, 10, 2, 20, false);
        Meeting meeting7 = new Meeting(17, 9, 21, 12, 1, 20, false);
        Meeting meeting8 = new Meeting(18, 9, 21, 8, 2, 20, false);
        Meeting meeting9 = new Meeting(19, 9, 21, 13, 4, 20, false);
        Meeting meeting10 = new Meeting(34, 9, 21, 14, 1, 20, false);
        Meeting meeting11 = new Meeting(35, 9, 21, 8, 5, 20, false);
        Meeting meeting12 = new Meeting(36, 9, 21, 10, 1, 20, false);
        Meeting meeting13 = new Meeting(37, 7, 21, 10, 1, 5, false);

        assertTrue(meeting.isSameMeeting(meeting1));
        assertFalse(meeting1.isSameMeeting(meeting2));
        assertFalse(meeting1.isSameMeeting(meeting3));
        assertFalse(meeting3.isSameMeeting(meeting4));
        assertTrue(meeting4.isSameMeeting(meeting5));
        assertTrue(meeting5.isSameMeeting(meeting6));
        assertTrue(meeting4.isSameMeeting(meeting7));
        assertFalse(meeting5.isSameMeeting(meeting8));
        assertFalse(meeting8.isSameMeeting(meeting9));
        assertFalse(meeting8.isSameMeeting(meeting10));
        assertFalse(meeting9.isSameMeeting(meeting11));
        assertTrue(meeting11.isSameMeeting(meeting12));
        assertFalse(meeting2.isSameMeeting(meeting13));

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

    @Test
    public void testSetCompleted() {
        assertFalse(meeting.getIsCompleted());
        meeting.setCompleted();
        assertTrue(meeting.getIsCompleted());
        Meeting meeting1 = new Meeting(3, 11, 14, 10, 5, 5, false);
        assertFalse(meeting1.getIsCompleted());
        meeting1.setCompleted();
        assertTrue(meeting1.getIsCompleted());
    }


    @Test
    public void testSetStatus() {
        meeting.setStatus(true);
        assertTrue(meeting.getIsCompleted());
        meeting.setStatus(false);
        assertFalse(meeting.getIsCompleted());
    }


    @Test
    public void testToString() {
        assertEquals("ID: 2; Month: 8; Day: 10; FromHour: 2; Duration: 2; RoomNO: 20; Status: false",
                meeting.toString());
        Meeting meeting1 = new Meeting(1, 10, 17, 6,
                4, 19, false);
        assertEquals("ID: 1; Month: 10; Day: 17; FromHour: 6; Duration: 4; RoomNO: 19; Status: false",
                meeting1.toString());
    }

    @Test
    public void testToJson() {
        Meeting meeting1 = new Meeting(1, 10, 17, 6,
                4, 19, false);

        JSONObject meetingJson = meeting1.toJson();
        assertEquals(1, meetingJson.getInt("meetingID"));
        assertEquals(10, meetingJson.getInt("month"));
        assertEquals(17, meetingJson.getInt("day"));
        assertEquals(6, meetingJson.getInt("fromHour"));
        assertEquals(4, meetingJson.getInt("duration"));
        assertEquals(19, meetingJson.getInt("roomNO"));
        assertFalse(meetingJson.getBoolean("isCompleted"));

    }


}
