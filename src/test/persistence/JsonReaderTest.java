package persistence;

import model.Meeting;
import model.MeetingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JasonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFoundMeetingList.json");
        try {
            MeetingList ml = reader.read();
            fail("IOException should been thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMeetingList() {
        String JSON_STORE = "./data/testReaderEmptyMeetingList.json";
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        JsonReader reader = new JsonReader(JSON_STORE);
        try {
            MeetingList ml = reader.read();
            assertEquals(27268507, ml.getStudentID());
            assertEquals(0, ml.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        String JSON_STORE = "./data/testReaderGeneralMeetingList.json";
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        JsonReader reader = new JsonReader(JSON_STORE);
        try {
            MeetingList ml = reader.read();
            assertEquals(27268507, ml.getStudentID());
            List<Meeting> meetingList = ml.getMeetingList();
            assertEquals(2, meetingList.size());
            checkMeeting(1,2,3,4,5,6,false, meetingList.get(0));
            checkMeeting(2,3,4,5,3,4,false, meetingList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
