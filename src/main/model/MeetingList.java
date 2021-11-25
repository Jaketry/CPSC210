package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import model.Event;
import model.EventLog;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

// Represents a list of meetings
// with maximum size MAX_ROOM_NO

public class MeetingList implements Writable {

    public static final int MAX_ROOM_NO = 20;

    private int studentID;

    private List<Meeting> meetingList;


    // EFFECTS: initialize meetingList as empty list
    public MeetingList(int studentID) {
        this.studentID = studentID;
        this.meetingList = new ArrayList<>();
    }

    // EFFECTS: returns studentID
    public int getStudentID() {
        return this.studentID;
    }

    // EFFECTS: returns an unmodifiable list of meeting in this meeting system
    public List<Meeting> getMeetingList() {
        return this.meetingList;
    }


    // MODIFIES: this
    // EFFECTS: return "Your meeting has been added!" if successful, "fail to add your meeting" otherwise
    public String addMeeting(Meeting meeting) {
        if (meeting.getRoomNO() <= MAX_ROOM_NO) {
            if (meetingList.isEmpty()) {
                meetingList.add(meeting);
                EventLog.getInstance().logEvent(new Event("Your meeting has been added"));
                return "Your meeting has been added!";
            }

            Boolean foundSameMeeting = false;
            for (Meeting m : meetingList) {
                if (meeting.isSameMeeting(m)) {
                    foundSameMeeting = true;
                    break;
                }
            }
            if (!foundSameMeeting) {
                meetingList.add(meeting);
                EventLog.getInstance().logEvent(new Event("Your meeting has been added"));
                return "Your meeting has been added!";
            }

        }
        EventLog.getInstance().logEvent(new Event("Fail to add your meeting"));
        return "Fail to add your meeting";
    }



    // EFFECTS: return room no if found, -1 otherwise
    public int getMeetingPlace(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                EventLog.getInstance().logEvent(new Event("Getting meeting place"));
                return meeting.getRoomNO();
            }
        }
        EventLog.getInstance().logEvent(new Event("Fail to get meeting place"));
        return -1;
    }



    // EFFECTS: return month for the given meetingID if found, -1 otherwise
    public int getMonth(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                EventLog.getInstance().logEvent(new Event("Getting meeting month"));
                return meeting.getMonth();
            }
        }
        EventLog.getInstance().logEvent(new Event("Fail to get meeting month"));
        return -1;
    }



    // EFFECTS: return day for the given meetingID if found, -1 otherwise
    public int getDay(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                EventLog.getInstance().logEvent(new Event("Getting meeting day"));
                return meeting.getDay();
            }
        }
        EventLog.getInstance().logEvent(new Event("Fail to get meeting day"));
        return -1;
    }



    // EFFECTS: return starting time for the given meetingID if found, -1 otherwise
    public int getFromHour(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                EventLog.getInstance().logEvent(new Event("Getting meeting fromHour"));
                return meeting.getFromHour();
            }
        }
        EventLog.getInstance().logEvent(new Event("Fail to meeting fromHour"));
        return -1;
    }

    // EFFECTS: return duration for the given meetingID if found, -1 otherwise
    public int getDuration(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                EventLog.getInstance().logEvent(new Event("Getting meeting duration"));
                return meeting.getDuration();
            }
        }
        EventLog.getInstance().logEvent(new Event("Fail to get meeting duration"));
        return -1;
    }

    // EFFECTS: return the size of meetingList
    public int length() {
        return meetingList.size();
    }


    // MODIFIES: this
    // EFFECTS: return true if meeting is cancelled successfully; false otherwise
    public Boolean cancelMeeting(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                meeting.setCompleted();
                meetingList.remove(meeting);
                EventLog.getInstance().logEvent(new Event("Your meeting has been removed"));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event("Fail to remove meeting"));
        return false;
    }


    // MODIFIES: this
    // EFFECTS: convert meetingList to a Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studentID", studentID);
        json.put("meetingList", meetingListToJson());
        return json;
    }

    // EFFECTS: returns meetings in this meeting system as a JSON array
    private JSONArray meetingListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meeting m : meetingList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

    public void printLog() {
        Iterator<Event> it = EventLog.getInstance().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }





}


