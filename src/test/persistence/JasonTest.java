package persistence;

import model.Meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JasonTest {

    protected void checkMeeting(int id, int month, int day, int fromHour, int duration, int roomNO, Boolean status,
                                Meeting meeting) {
        assertEquals(id, meeting.getMeetingID());
        assertEquals(month, meeting.getMonth());
        assertEquals(day, meeting.getDay());
        assertEquals(fromHour, meeting.getFromHour());
        assertEquals(duration, meeting.getDuration());
        assertEquals(roomNO, meeting.getRoomNO());
        assertEquals(status, meeting.getIsCompleted());
    }
}
