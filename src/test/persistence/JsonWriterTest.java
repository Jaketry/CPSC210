package persistence;

import model.Meeting;
import model.MeetingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JasonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MeetingList wr = new MeetingList(27268507);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:meetinglist.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMeetingList() {
        try {
            MeetingList ml = new MeetingList(27268507);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMeetingList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMeetingList.json");
            MeetingList meetingList = reader.read();
            assertEquals(27268507, meetingList.getStudentID());
            assertEquals(0, meetingList.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMeetingList() {
        try {
            MeetingList ml = new MeetingList(27268507);
            Meeting meeting = new Meeting(2, 8, 10, 2, 2, 20, false);
            Meeting meeting1 = new Meeting(3, 11, 10, 10, 3, 5, false);
            ml.addMeeting(meeting);
            ml.addMeeting(meeting1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMeetingList.json");
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMeetingList.json");
            MeetingList meetingList = reader.read();
            assertEquals(27268507, meetingList.getStudentID());
            List<Meeting> meetingList1 = meetingList.getMeetingList();
            assertEquals(2, meetingList1.size());
            checkMeeting(2,8,10,2,2,20,false, meetingList1.get(0));
            checkMeeting(3,11,10,10,3,5,false, meetingList1.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
