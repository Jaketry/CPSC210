package model;

public class Meeting {

    private int meetingID;
    private int month;
    private int day;
    private int fromHour;
    private int duration;
    private int roomNO;
    private Boolean isCompleted;

    public Meeting(int meetingID, int month, int day, int fromHour, int duration, int roomNO) {
        this.meetingID = meetingID;
        this.month = month;
        this.day = day;
        this.fromHour = fromHour;
        this.duration = duration;
        this.roomNO = roomNO;
        this.isCompleted = false;
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

    // MODIFIES: this
    // EFFECTS: set a meeting's status as "complete"
    public void setCompleted() {
        this.isCompleted = true;
    }

    // EFFECTS: return true if two meeting are the same, false otherwise
    public Boolean isSameMeeting(Meeting meeting) {
        return this.roomNO == meeting.getRoomNO()
                && this.month == meeting.getMonth() && this.day == meeting.getDay()
                && this.fromHour <= meeting.getFromHour() && meeting.getFromHour() < this.fromHour + this.duration;
    }

    // MODIFIES: this
    // EFFECTS: set the meetingID as given n
    public void setMeetingID(int n) {
        this.meetingID = n;
    }

    // MODIFIES: this
    // EFFECTS: set the meetingMonth as given n
    public void setMonth(int n) {
        this.month = n;
    }

    // MODIFIES: this
    // EFFECTS: set the meetingDay as given n
    public void setDay(int n) {
        this.day = n;
    }

    // MODIFIES: this
    // EFFECTS: set the meeting starting time as given n
    public void setFromHour(int n) {
        this.fromHour = n;
    }

    // MODIFIES: this
    // EFFECTS: set the meetingDuration as given n
    public void setDuration(int n) {
        this.duration = n;
    }

    // MODIFIES: this
    // EFFECTS: set the roomNO as given n
    public void setRoomNO(int n) {
        this.roomNO = n;
    }
}
