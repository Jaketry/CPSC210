package model;

import org.json.JSONObject;
import persistence.Writable;

public class Meeting implements Writable {

    // we use 24/7 time for this meeting system;
    // duration must be at least 1 hour;
    // room no less than or equal 20;

    private int meetingID;
    private int month;
    private int day;
    private int fromHour;
    private int duration;
    private int roomNO;
    private Boolean isCompleted;


    // EFFECTS: construct a Meeting with given information
    public Meeting(int meetingID, int month, int day, int fromHour, int duration, int roomNO, Boolean isCompleted) {
        this.meetingID = meetingID;
        this.month = month;
        this.day = day;
        this.fromHour = fromHour;
        this.duration = duration;
        this.roomNO = roomNO;
        this.isCompleted = isCompleted;
    }


    // EFFECTS: return meetingID for a meeting
    public int getMeetingID() {
        return this.meetingID;
    }

    // EFFECTS: return roomNO for a meeting
    public int getRoomNO() {
        return this.roomNO;
    }

    // EFFECTS: return month for a meeting
    public int getMonth() {
        return this.month;
    }

    // EFFECTS: return day for a meeting
    public int getDay() {
        return this.day;
    }

    // EFFECTS: return starting time for a meeting
    public int getFromHour() {
        return this.fromHour;
    }

    // EFFECTS: return duration for a meeting
    public int getDuration() {
        return this.duration;
    }

    // EFFECTS: return status for a meeting ("completed" or "Incomplete")
    public Boolean getIsCompleted() {
        return this.isCompleted;
    }


    // EFFECTS: return true if two meeting are the same, false otherwise
    public Boolean isSameMeeting(Meeting meeting) {
        return this.roomNO == meeting.getRoomNO()
                && this.month == meeting.getMonth() && this.day == meeting.getDay()
                && ((this.fromHour <= meeting.getFromHour() && meeting.getFromHour() < this.fromHour + this.duration)
                || (meeting.getFromHour() < this.fromHour
                && this.fromHour < meeting.getFromHour() + meeting.getDuration()));
    }

    // EFFECTS: return meeting in a string
    public String toString() {
        return "ID: " + meetingID + "; Month: " + month + "; Day: " + day + "; FromHour: " + fromHour
                + "; Duration: " + duration + "; RoomNO: " + roomNO + "; Status: " + isCompleted;
    }

    // MODIFIES: this
    // EFFECTS: set the meetingID as given n
    public void setMeetingID(int n) {
        this.meetingID = n;
    }

    // REQUIRES: n is natural number from 1 to 12
    // MODIFIES: this
    // EFFECTS: set the meetingMonth as given n
    public void setMonth(int n) {
        this.month = n;
    }

    // REQUIRES: n is natural number from 1 to 31
    // MODIFIES: this
    // EFFECTS: set the meetingDay as given n
    public void setDay(int n) {
        this.day = n;
    }

    // REQUIRES: n is natural number from 0 to 24
    // MODIFIES: this
    // EFFECTS: set the meeting starting time as given n
    public void setFromHour(int n) {
        this.fromHour = n;
    }

    // REQUIRES: n less than 5
    // MODIFIES: this
    // EFFECTS: set the meetingDuration as given n
    public void setDuration(int n) {
        this.duration = n;
    }

    // REQUIRES: n less than 20
    // MODIFIES: this
    // EFFECTS: set the roomNO as given n
    public void setRoomNO(int n) {
        this.roomNO = n;
    }

    // MODIFIES: this
    // EFFECTS: set a meeting's status as "complete"
    public void setCompleted() {
        this.isCompleted = true;
    }

    // MODIFIES: this
    // EFFECTS: set a meeting's status as "complete"
    public void setStatus(Boolean status) {
        this.isCompleted = status;
    }



    // MODIFIES: this
    // EFFECTS: convert meeting to a Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("meetingID", meetingID);
        json.put("month", month);
        json.put("day", day);
        json.put("fromHour", fromHour);
        json.put("duration", duration);
        json.put("roomNO", roomNO);
        json.put("isCompleted", isCompleted);
        return json;
    }
}

