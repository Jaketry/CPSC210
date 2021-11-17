package ui;

import model.Meeting;
import model.MeetingList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class Gui extends JFrame {

    // a graphical user interface for meeting list;
    private static final String JSON_STORE = "./data/meetingList.json";
    private MeetingList meetingList = new MeetingList(27268507);
    private Container container;

    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new  JsonReader(JSON_STORE);

    private JPanel panelTop = new JPanel();
    private JPanel panelLeft = new JPanel();
    private JPanel panelRight = new JPanel();
    private JPanel panelList = new JPanel();
    private JPanel panelOutput = new JPanel();

    private JButton button1 = new JButton("Add");
    private JButton button2 = new JButton("Remove");
    private JButton button3 = new JButton("Save");
    private JButton button4 = new JButton("Clear");
    private JButton button5 = new JButton("Print");
    private JButton button6 = new JButton("Load");
    private JButton button7 = new JButton("Get Meeting Place");
    private JButton button8 = new JButton("Get Meeting Month");
    private JButton button9 = new JButton("Get Meeting Day");
    private JButton button10 = new JButton("Get Meeting FromHour");
    private JButton button11 = new JButton("Get Meeting Duration");
    private JButton button12 = new JButton("Image");


    private JLabel label1 = new JLabel("Meeting ID");
    private JTextField textField1 = new JTextField();
    private JLabel label2 = new JLabel("Month");
    private JTextField textField2 = new JTextField();
    private JLabel label3 = new JLabel("Day");
    private JTextField textField3 = new JTextField();
    private JLabel label4 = new JLabel("FromHour");
    private JTextField textField4 = new JTextField();
    private JLabel label5 = new JLabel("Duration");
    private JTextField textField5 = new JTextField();
    private JLabel label6 = new JLabel("Room No");
    private JTextField textField6 = new JTextField();
    private JLabel label7 = new JLabel("Status");
    private JTextField textField7 = new JTextField();

    private int meetingID;

    private JList<Meeting> list = new JList<>();
    DefaultListModel<Meeting> model = new DefaultListModel<>();

    private JList<String> list1 = new JList<>();
    DefaultListModel<String> model1 = new DefaultListModel<>();

    private JFrame frame1;
    private JLabel label;
    private ImageIcon image;


    // // EFFECTS: create a gui constructor with a JFrame;
    public Gui() {
        JFrame frame = new JFrame("My Meeting List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 900);
        container = frame.getContentPane();
        container.setLayout(new BorderLayout(8,6));
        container.setBackground(Color.WHITE);
        allPanel();
        frame.pack();
        frame.setSize(1100, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: set up a JList for display meeting when "add" is clicked;
    public void listModel() {
        // visualize x and y
        list.setModel(model);
        panelList.setLayout(new GridLayout(1,1,2,2));
        panelList.add(new JScrollPane(list));
    }


    // MODIFIES: this
    // EFFECTS: set up a JList to visualize output;
    public void list1Model() {
        // visualize output
        list1.setModel(model1);
        panelOutput.setLayout(new GridLayout(1,1,2,2));
        panelOutput.add(new JScrollPane(list1));
    }

    // MODIFIES: this
    // EFFECTS: create a panel at the top of JFrame and added buttons in this panel
    public void topPanel() {
        //PanelTop
        panelTop.setBorder(new LineBorder(Color.BLACK, 3));
        panelTop.setBackground(Color.YELLOW);
        panelTop.setLayout(new FlowLayout(9));
        container.add(panelTop, BorderLayout.NORTH);

        // Buttons
        panelTop.add(button1);
        panelTop.add(button2);
        panelTop.add(button3);
        panelTop.add(button4);
        panelTop.add(button5);
        panelTop.add(button6);
        panelTop.add(button12);
    }

    // MODIFIES: this
    // EFFECTS: added meeting to meeting list when "add" button is clicked;
    public void button1Action() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkEmptyString();
                int meetingID = Integer.parseInt(textField1.getText()); // convert string to int
                int month = Integer.parseInt(textField2.getText());
                int day = Integer.parseInt(textField3.getText());
                int fromHour = Integer.parseInt(textField4.getText());
                int duration = Integer.parseInt(textField5.getText());
                int roomNo = Integer.parseInt(textField6.getText());
                Boolean status = Boolean.parseBoolean(textField7.getText());
                Meeting meeting = new Meeting(meetingID, month, day, fromHour, duration, roomNo, status);
                int size = meetingList.getMeetingList().size();
                model1.addElement(meetingList.addMeeting(meeting));
                if (meetingList.getMeetingList().size() > size) {
                    model.addElement(meeting);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: remove meeting from meeting list when "remove" is clicked;
    public void button2Action() {
        // Actions for button 2 ("remove")
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showInputDialog("Please enter your meeting ID").equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in your meeting ID!",
                            "Meeting Error", JOptionPane.ERROR_MESSAGE);
                }
                meetingID = Integer.parseInt(JOptionPane.showInputDialog("Please enter your meeting ID"));
                model1.addElement(doCancelMeeting());

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: save all changes made when "save" is clicked;
    public void button3Action() {
        //Actions for button 3 ("save")
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model1.addElement(saveMeetingList());

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: clear all inputs when "clear" is clicked;
    public void button4Action() {
        //Actions for button 4 ("clear")
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");

            }
        });
    }


    // EFFECTS: print all meetings when "print" is clicked;
    public void button5Action() {
        //Actions for button 5 ("print")
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printMeetingList();
                //model1.addElement(printMeetingList());
            }
        });
    }


    // EFFECTS: load all meetings to meeting list when "load" is clicked;
    public void button6Action() {
        //Actions for button 6 ("load")
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model1.addElement(loadMeetingSystem());

            }
        });
    }

    // EFFECTS: get meeting place from given meeting ID when "Get Meeting Place" is clicked;
    public void button7Action() {
        //Actions for button 7 ("Get Meeting Place")
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meetingID = Integer.parseInt(JOptionPane.showInputDialog("Please enter your meeting ID"));
                model1.addElement(doGetPlace());
            }
        });
    }

    // EFFECTS: get meeting month from given meeting ID when "Get Meeting Month" is clicked;
    public void button8Action() {
        // Actions for button 8 ("Get Month")
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meetingID = Integer.parseInt(JOptionPane.showInputDialog("Please enter your meeting ID"));
                model1.addElement(doGetMonth());
            }
        });
    }

    // EFFECTS: get meeting day from given meeting ID when "Get Meeting Day" is clicked;
    public void button9Action() {
        //Actions for button 9 ("Get Day")
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meetingID = Integer.parseInt(JOptionPane.showInputDialog("Please enter your meeting ID"));
                model1.addElement(doGetDay());
            }
        });
    }

    // EFFECTS: get meeting fromHour from given meeting ID when "Get Meeting FromHour" is clicked;
    public void button10Action() {
        //Actions for button 10 ("Get FromHour")
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meetingID = Integer.parseInt(JOptionPane.showInputDialog("Please enter your meeting ID"));
                model1.addElement(doGetFromHour());
            }
        });
    }

    // EFFECTS: get meeting duration from given meeting ID when "Get Meeting Duration" is clicked;
    public void button11Action() {
        //Actions for button 11 ("Get Duration")
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meetingID = Integer.parseInt(JOptionPane.showInputDialog("Please enter your meeting ID"));
                model1.addElement(doGetDuration());
            }
        });
    }

    // EFFECTS: display image in data file when "image" button is clicked
    public void button12Action() {
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1 = new JFrame("My Image");
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                    image = new ImageIcon("./data/tobs.jpg");
                    label = new JLabel(image);
                    frame1.add(label);
                } catch (Exception exception) {
                    System.out.println("Image is not found");
                }
                frame1.setSize(900,700);
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }
        });
    }




    // MODIFIES: this
    // EFFECTS: put all panels together and assign actions for each button
    public void allPanel() {
        listModel();
        list1Model();
        topPanel();
        button1Action();
        button2Action();
        button3Action();
        button4Action();
        button5Action();
        button6Action();
        button7Action();
        button8Action();
        button9Action();
        button10Action();
        button11Action();
        button12Action();
        leftPanel();
        rightPanel();
    }

    // MODIFIES: this
    // EFFECTS: set up the left panel and add buttons;
    public void leftPanel() {
        //PanelLeft
        panelLeft.setBorder(new LineBorder(Color.BLACK, 3));
        panelLeft.setPreferredSize(new Dimension(546,400));
        panelLeft.setBackground(Color.YELLOW);
        panelLeft.setLayout(new GridLayout(19,1));
        container.add(panelLeft, BorderLayout.LINE_START);
        leftPanelButton();
    }

    // MODIFIES: this
    // EFFECTS: set up the right panel and two JLists;
    public void rightPanel() {
        panelRight.setBorder(new LineBorder(Color.BLACK, 3));
        panelRight.setPreferredSize(new Dimension(546,400));
        panelRight.setBackground(Color.YELLOW);
        container.add(panelRight, BorderLayout.LINE_END);

        //PanelRight
        panelList.setBorder(new LineBorder(Color.BLACK, 3));
        panelList.setPreferredSize(new Dimension(530,346));
        panelList.setBackground(Color.YELLOW);
        panelRight.add(panelList, BorderLayout.NORTH);

        //PanelOutput
        panelOutput.setBorder(new LineBorder(Color.BLACK, 3));
        panelOutput.setPreferredSize(new Dimension(530,346));
        panelOutput.setBackground(Color.YELLOW);
        panelRight.add(panelOutput, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: saves the meetingList to file
    public String saveMeetingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(meetingList);
            jsonWriter.close();
            return "Saved " + meetingList.getStudentID() + " to " + JSON_STORE;
        } catch (FileNotFoundException e) {
            return "Unable to write to file: " + JSON_STORE;
        }
    }

    // MODIFIES: this
    // EFFECTS: cancel meeting based on given ID
    public String doCancelMeeting() {
        Boolean isCancel = meetingList.cancelMeeting(meetingID);
        if (isCancel) {
            return "Your meeting has been cancelled!";
        } else {
            return "not found your meeting";
        }
    }

    // EFFECTS: prints all the meeting list in meeting system to the console
    public void printMeetingList() {
        List<Meeting> meetingList1 = meetingList.getMeetingList();

        for (Meeting m : meetingList1) {
            model1.addElement(m.toString());
        }

    }

    // EFFECTS: loads meeting system from file
    public String loadMeetingSystem() {
        try {
            meetingList = jsonReader.read();
            return "Loaded " + meetingList.getStudentID() + " from " + JSON_STORE;
        } catch (IOException e) {
            return "Unable to read from file: " + JSON_STORE;
        }
    }

    // EFFECTS: review the place for the meeting based on the given meetingID
    public String doGetPlace() {
        int roomNO = meetingList.getMeetingPlace(meetingID);
        return "Your meeting room no is " + roomNO;
    }

    // EFFECTS: review the month for the meeting based on the given meetingID
    public String doGetMonth() {
        int month = meetingList.getMonth(meetingID);
        return "The month for your meeting is " + month;
    }

    // EFFECTS: review the day for the meeting based on the given meetingID
    public String doGetDay() {
        int day = meetingList.getDay(meetingID);
        return "Your meeting date is " + day;
    }

    // EFFECTS: review the starting time for the meeting based on the given meetingID
    public String doGetFromHour() {
        int time = meetingList.getFromHour(meetingID);
        return "Your meeting starts from " + time;
    }

    // EFFECTS: review the duration for the meeting based on the given meetingID
    public String doGetDuration() {
        int duration = meetingList.getDuration(meetingID);
        return "The duration for your meeting is " + duration;
    }


    // EFFECTS: check whether inputs are empty string and pop up a error dialog if so
    public void checkEmptyString() {
        if (textField1.getText().equals("") || textField2.getText().equals("")
                || textField3.getText().equals("") || textField4.getText().equals("")
                || textField5.getText().equals("") || textField6.getText().equals("")
                || textField7.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill in your meeting!",
                    "Meeting Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: add buttons, textFields and labels for left panel;
    public void leftPanelButton() {
        panelLeft.add(label1);
        panelLeft.add(textField1);
        panelLeft.add(label2);
        panelLeft.add(textField2);
        panelLeft.add(label3);
        panelLeft.add(textField3);
        panelLeft.add(label4);
        panelLeft.add(textField4);
        panelLeft.add(label5);
        panelLeft.add(textField5);
        panelLeft.add(label6);
        panelLeft.add(textField6);
        panelLeft.add(label7);
        panelLeft.add(textField7);
        panelLeft.add(button7);
        panelLeft.add(button8);
        panelLeft.add(button9);
        panelLeft.add(button10);
        panelLeft.add(button11);
    }




}
