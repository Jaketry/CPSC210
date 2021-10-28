package model;


import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MeetingListTest {

    private MeetingList meetingList;

    @BeforeEach
    public void runBefore() {
        meetingList = new MeetingList(27268507);
    }

    @Test
    public void testMeetingList() {
        assertEquals(0, meetingList.length());
    }

    @Test
    public void testAddMeeting() {

        Meeting meeting = new Meeting(1, 10, 8, 13, 2, 15, false);
        assertEquals("Your meeting has been added!", meetingList.addMeeting(meeting));
        assertEquals(1, meetingList.length());
        assertEquals(10, meetingList.getMonth(1));
        assertEquals(8, meetingList.getDay(1));
        meetingList.addMeeting(meeting);
        assertEquals(1, meetingList.length());
        Meeting meeting1 = new Meeting(10, 10, 1, 10, 2, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(2, meetingList.length());
        Meeting meeting2 = new Meeting(10, 10, 1, 10, 2, 25, false);
        meetingList.addMeeting(meeting2);
        assertEquals(2, meetingList.length());


    }


    @Test
    public void testGetMeetingPlace() {
        assertEquals(-1, meetingList.getMeetingPlace(2));
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15, false);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 10, 1, 10, 2, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(20, meetingList.getMeetingPlace(10));
        assertEquals(-1, meetingList.getMeetingPlace(7));
    }

    @Test
    public void testGetMonth() {
        assertEquals(-1, meetingList.getMonth(2));
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15, false);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 2, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(2, meetingList.length());
        assertEquals(5, meetingList.getMonth(10));
        assertEquals(-1, meetingList.getMonth(6));
    }

    @Test
    public void testGetDay() {
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15, false);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 2, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(1, meetingList.getDay(10));
        assertEquals(-1, meetingList.getDay(5));
    }

    @Test
    public void testGetFromHour() {
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15, false);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 1, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(10, meetingList.getFromHour(10));
        assertEquals(-1, meetingList.getFromHour(7));
    }

    @Test
    public void testGetDuration() {
        Meeting meeting = new Meeting(7, 4, 28, 13, 2, 15, false);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(16, 8, 7, 12, 1, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(2, meetingList.getDuration(7));
        assertEquals(-1, meetingList.getDuration(8));
        assertEquals(1, meetingList.getDuration(16));
    }



    @Test
    void testLength() {
        assertEquals(0, meetingList.length());
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15, false);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 1, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(2, meetingList.length());
    }

    @Test
    public void testCancelMeeting() {
        Meeting meeting = new Meeting(3, 10, 8, 13, 2, 15, false);
        meetingList.addMeeting(meeting);
        Meeting meeting1 = new Meeting(10, 5, 1, 10, 1, 20, false);
        meetingList.addMeeting(meeting1);
        assertEquals(true, meetingList.cancelMeeting(10));
        assertEquals(1, meetingList.length());
        assertEquals(false, meetingList.cancelMeeting(4));
        assertEquals(true, meetingList.cancelMeeting(3));
        assertEquals(0, meetingList.length());
    }


    @Test
    public void testMeetingListToJson() {
        MeetingList meetingList = new MeetingList(27268507);
        JSONObject meetingList1 = meetingList.toJson();
        assertEquals(0, meetingList1.getJSONArray("meetingList").toList().size());
        assertEquals(27268507, meetingList1.getInt("studentID"));

    }

}