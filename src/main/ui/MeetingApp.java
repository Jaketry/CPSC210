package ui;

import model.Meeting;
import model.MeetingList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MeetingApp {

    private static final String JSON_STORE = "./data/meetingList.json";
    private MeetingList meetingSystem;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs meeting system and runs application
    public MeetingApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        meetingSystem = new MeetingList(27268507);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMeeting();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMeeting() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

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
        } else if (command.equals("i")) {
            printMeetingList();
        } else if (command.equals("s")) {
            saveMeetingSystem();
        } else if (command.equals("r")) {
            loadMeetingSystem();
        } else {
            System.out.println("Selection not valid...");
        }
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
        System.out.println("\ti -> print meeting list");
        System.out.println("\ts -> save meeting system to file");
        System.out.println("\tr -> load meeting system from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add a meeting to the meetingList if it does not exist in the list
    private void doAddMeeting() {
        System.out.print("Enter your meetingID: ");
        int id = input.nextInt();

        System.out.print("Enter your meetingMonth: ");
        int month = input.nextInt();

        System.out.print("Enter your meetingDay: ");
        int day = input.nextInt();

        System.out.print("Enter your meetingFromHour: ");
        int fromHour = input.nextInt();

        System.out.print("Enter your meetingDuration: ");
        int duration = input.nextInt();

        System.out.print("Enter your meetingRoomNO: ");
        int roomNO = input.nextInt();

        System.out.print("Enter your meeting status: ");
        Boolean status = input.nextBoolean();

        Meeting m = new Meeting(id, month, day, fromHour, duration, roomNO, status);
        printMeeting(m);
    }


    // EFFECTS: review the place for the meeting based on the given meetingID
    private void doGetPlace() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int roomNO = meetingSystem.getMeetingPlace(meetingID);
        System.out.println("Your meeting room no is " + roomNO);
    }

    // EFFECTS: review the month for the meeting based on the given meetingID
    private void doGetMonth() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int month = meetingSystem.getMonth(meetingID);
        System.out.println("The month for your meeting is " + month);
    }


    // EFFECTS: review the day for the meeting based on the given meetingID
    private void doGetDay() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int day = meetingSystem.getDay(meetingID);
        System.out.println("Your meeting date is " + day);
    }


    // EFFECTS: review the starting time for the meeting based on the given meetingID
    private void doGetFromHour() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int time = meetingSystem.getFromHour(meetingID);
        System.out.println("Your meeting starts from " + time);
    }

    // EFFECTS: review the duration for the meeting based on the given meetingID
    private void doGetDuration() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        int duration = meetingSystem.getDuration(meetingID);
        System.out.println("The duration for your meeting is " + duration);
    }


    // MODIFIES: this
    // EFFECTS: cancel the meeting based on the given meetingID
    private void doCancelMeeting() {
        System.out.print("Enter your meetingID: ");
        int meetingID = input.nextInt();
        Boolean isCancel = meetingSystem.cancelMeeting(meetingID);
        if (isCancel) {
            System.out.println("Your meeting has been cancelled!");
        } else {
            System.out.println("not found your meeting");
        }
    }


    // EFFECTS: prints all the meeting list in meeting system to the console
    private void printMeetingList() {
        List<Meeting> meetingList = meetingSystem.getMeetingList();

        for (Meeting m : meetingList) {
            System.out.println(m.toString());
        }
    }

    // EFFECTS: saves the meetingSystem to file
    private void saveMeetingSystem() {
        try {
            jsonWriter.open();
            jsonWriter.write(meetingSystem);
            jsonWriter.close();
            System.out.println("Saved " + meetingSystem.getStudentID() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads meeting system from file
    private void loadMeetingSystem() {
        try {
            meetingSystem = jsonReader.read();
            System.out.println("Loaded " + meetingSystem.getStudentID() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }




    // EFFECTS: prints whether the meeting has been added successfully
    private void printMeeting(Meeting meeting) {
        System.out.println(meetingSystem.addMeeting(meeting));
    }


}
