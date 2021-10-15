package ui;

import model.Meeting;
import model.MeetingSystem;

import java.util.Scanner;

public class MeetingApp {
    private MeetingSystem meetingList = new MeetingSystem();
    private Meeting meeting;
    private Scanner input;

    // EFFECTS: runs the meeting application
    public MeetingApp() {
        runMeeting();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMeeting() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nHave a good day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddMeeting();
        } else if (command.equals("p")) {
            doGetPlace();
        } else if (command.equals("m")) {
            doGetMonth();
        } else if (command.equals("d")) {
            doGetDay();
        } else if (command.equals("t")) {
            doGetFromHour();
        } else if (command.equals("l")) {
            doGetDuration();
        } else if (command.equals("c")) {
            doCancelMeeting();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes meeting
    private void init() {
        meeting = new Meeting(1, 10, 15, 9, 2, 1);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> addMeeting");
        System.out.println("\tp -> getPlace");
        System.out.println("\tm -> getMonth");
        System.out.println("\td -> getDay");
        System.out.println("\tt -> getFromHour");
        System.out.println("\tl -> getDuration");
        System.out.println("\tc -> cancelMeeting");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add a meeting to the meetingList if it does not exist in the list
    private void doAddMeeting() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        meeting.setMeetingID(meetingID);

        System.out.print("Enter your meetingMonth: ");
        int meetingMonth = input.nextInt();
        meeting.setMonth(meetingMonth);

        System.out.print("Enter your meetingDay: ");
        int meetingDay = input.nextInt();
        meeting.setDay(meetingDay);

        System.out.print("Enter your meetingFromHour: ");
        int meetingFromHour = input.nextInt();
        meeting.setFromHour(meetingFromHour);

        System.out.print("Enter your meetingDuration: ");
        int meetingDuration = input.nextInt();
        meeting.setDuration(meetingDuration);

        System.out.print("Enter your meetingRoomNO: ");
        int meetingRoomNO = input.nextInt();
        if (meetingRoomNO <= 20 && 1 <= meetingRoomNO) {
            meeting.setRoomNO(meetingRoomNO);
        } else {
            System.out.println("Invalid Room NO...\n");
        }

        printMeeting(meeting);
    }


    // EFFECTS: review the place for the meeting based on the given meetingID
    private void doGetPlace() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int roomNO = meetingList.getMeetingPlace(meetingID);
        System.out.println("Your meeting room no is " + roomNO);
    }

    // EFFECTS: review the month for the meeting based on the given meetingID
    private void doGetMonth() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int month = meetingList.getMonth(meetingID);
        System.out.println("The month for your meeting is " + month);
    }


    // EFFECTS: review the day for the meeting based on the given meetingID
    private void doGetDay() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int day = meetingList.getDay(meetingID);
        System.out.println("Your meeting date is " + day);
    }


    // EFFECTS: review the starting time for the meeting based on the given meetingID
    private void doGetFromHour() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int time = meetingList.getFromHour(meetingID);
        System.out.println("Your meeting starts from " + time);
    }

    // EFFECTS: review the duration for the meeting based on the given meetingID
    private void doGetDuration() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int duration = meetingList.getDuration(meetingID);
        System.out.println("The duration for your meeting is " + duration);
    }


    // MODIFIES: this
    // EFFECTS: cancel the meeting based on the given meetingID
    private void doCancelMeeting() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        Boolean isCancel = meetingList.cancelMeeting(meetingID);
        if (isCancel) {
            System.out.println("Your meeting has been cancelled!");
        } else {
            System.out.println("not found your meeting");
        }
    }


    // EFFECTS: prints whether the meeting has been added successfully
    private void printMeeting(Meeting meeting) {
        System.out.println(meetingList.addMeeting(meeting));
    }


}
