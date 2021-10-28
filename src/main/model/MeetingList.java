package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;
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
                return "Your meeting has been added!";
            }
        }
        return "fail to add your meeting";
    }



    // EFFECTS: return room no if found, -1 otherwise
    public int getMeetingPlace(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                return meeting.getRoomNO();
            }
        }
        return -1;
    }



    // EFFECTS: return month for the given meetingID if found, -1 otherwise
    public int getMonth(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                return meeting.getMonth();
            }
        }
        return -1;
    }



    // EFFECTS: return day for the given meetingID if found, -1 otherwise
    public int getDay(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                return meeting.getDay();
            }
        }
        return -1;
    }



    // EFFECTS: return starting time for the given meetingID if found, -1 otherwise
    public int getFromHour(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                return meeting.getFromHour();
            }
        }
        return -1;
    }

    // EFFECTS: return duration for the given meetingID if found, -1 otherwise
    public int getDuration(int meetingID) {
        for (Meeting meeting : meetingList) {
            if (meeting.getMeetingID() == meetingID) {
                return meeting.getDuration();
            }
        }
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
                return true;
            }
        }
        return false;
    }



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


}


