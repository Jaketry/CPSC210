package persistence;

import model.Meeting;
import model.MeetingList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;


public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads meeting system from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MeetingList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMeetingSystem(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses meeting system from JSON object and returns it
    private MeetingList parseMeetingSystem(JSONObject jsonObject) {
        int studentID = jsonObject.getInt("studentID");
        MeetingList ms = new MeetingList(studentID);
        addMeetingSystem(ms, jsonObject);
        return ms;
    }

    // MODIFIES: ms
    // EFFECTS: parses meetingList from JSON object and adds them to meeting system
    private void addMeetingSystem(MeetingList ms, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("meetingList");
        for (Object json : jsonArray) {
            JSONObject nextMeeting = (JSONObject) json;
            addMeeting(ms, nextMeeting);
        }
    }

    // MODIFIES: ms
    // EFFECTS: parses meeting from JSON object and adds it to meeting system
    private void addMeeting(MeetingList ms, JSONObject jsonObject) {
        int meetingID = jsonObject.getInt("meetingID");
        int month = jsonObject.getInt("month");
        int day = jsonObject.getInt("day");
        int fromHour = jsonObject.getInt("fromHour");
        int duration = jsonObject.getInt("duration");
        int roomNO = jsonObject.getInt("roomNO");
        Boolean isCompleted = jsonObject.getBoolean("isCompleted");
        Meeting meeting = new Meeting(meetingID, month, day, fromHour, duration, roomNO, isCompleted);
        ms.addMeeting(meeting);
    }






}
