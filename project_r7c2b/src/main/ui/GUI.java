package ui;

import exceptions.TooLong;
import model.*;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.border.Border;


import static network.API.obtainInfo;
import static network.API.produceStart;

// taken from javatutorial,stackoverflow,oracle
// https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
// https://stackoverflow.com/questions/36561618/swing-java-multiple-buttons-gui
// https://www.javatpoint.com/java-swing

public class GUI extends Frame implements ActionListener {
    Font font = new Font("Times New Roman", Font.BOLD, 12);
    Border loweredborder = BorderFactory.createLoweredBevelBorder();
    TodoList list = new TodoList();
    ExamReminderFunctionality erf = new ExamReminderFunctionality();

    static JTextArea text1 = new JTextArea(4, 50);
    static JTextArea console = new JTextArea(18, 35);
    static JTextArea reminder = new JTextArea(2, 12);
    static JButton userinput = new JButton("Press to add Item and Time");
    static JButton deleteitem = new JButton("Press to delete Item");
    static JButton exambutton = new JButton("Press to add Exam");
    static JButton deleteexam = new JButton("Press to delete Exam");
    static JButton locationbutton = new JButton("Press to see Locations and Time");


    //EFFECTS: Runs Program

    public static void main(String[] args) throws IOException, ParseException {
        obtainInfo();
        produceLocalTime();
        GUI myWindow = new GUI("Todo List");
        myWindow.setSize(600, 500);
        myWindow.setVisible(true);

    }


    public GUI(String title) {
        super(title);
        add(text1);
        add(userinput);
        add(console);
        add(deleteitem);
        add(exambutton);
        add(deleteexam);
        add(locationbutton);
        add(reminder);
        console.setFont(font);
        userinput.setFont(font);
        deleteitem.setFont(font);
        exambutton.setFont(font);
        deleteexam.setFont(font);
        reminder.setFont(font);
        locationbutton.setFont(font);
        exambutton.addActionListener(this);
        userinput.addActionListener(this);
        deleteitem.addActionListener(this);
        console.setBounds(230, 160, 350, 300);
        console.setBorder(loweredborder);
        reminder.setBorder(loweredborder);
        userinput.setBounds(10, 200, 200, 40);
        reminder.setBounds(10, 150, 200, 40);
        setBackground(Color.PINK);
        text1.setBounds(10, 80, 580, 55);
        text1.setBorder(loweredborder);
        text1.setFont(font);
        deleteitem.setBounds(10, 250, 200, 40);
        exambutton.setBounds(10, 300, 200, 40);
        deleteexam.setBounds(10, 350, 200, 40);
        locationbutton.setBounds(10, 400, 200, 40);
        locationbutton.addActionListener(this);
        deleteexam.addActionListener(this);
        reminder.setLineWrap(true);
        reminder.setWrapStyleWord(true);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        reminder.setBackground(Color.red);
        reminder.setText("No Operations Are in Use");
        setLayout(null);


    }

    private static void produceLocalTime() throws IOException, ParseException {
        text1.setText(produceStart());
    }


    private void formatter() {
        console.setText("");
        console.append("Stored Items:\n");
        for (AllItem it : list.getList()) {
            console.append("TASK: " + it.getName() + " at the time of " + it.getTime() + "\n");
        }
    }

    private void todolistOperationCancel() {
        formatter();
        console.append("\n Operation has been cancelled!");

    }


    private void selectedRegularItem() {
        String input = JOptionPane.showInputDialog(null, "Please Enter Item: ");
        if (input == (null)) {
            todolistOperationCancel();
            return;
        }
        String input2 = JOptionPane.showInputDialog(null, "Please Enter Time: ");
        if (input2 == (null)) {
            todolistOperationCancel();
            return;
        }
        Item it = new Item(input, input2);
        try {
            list.addItem(it);
            formatter();
        } catch (TooLong tooLong) {
            JOptionPane.showMessageDialog(null, "list is over 10, you must delete an Item to add new Items!");
            buttonDeletePressed();
        }
    }


    private void buttonDeletePressed() {
        String deleteNamePlusTime = JOptionPane.showInputDialog(null, "Please Enter Item to Delete: ");
        if (deleteNamePlusTime == (null)) {
            todolistOperationCancel();
            return;
        }
        for (int i = 0; list.getList().size() > i; i++) {
            if (list.getList().get(i).getName().equals(deleteNamePlusTime) == true) {
                list.addObserver(list.getList().get(i));
                list.deleteItem(list.getList().get(i));
                console.setText("");
                formatter();
                console.append("\n");
                console.append(list.notifyObserverGui(list.getList()));
            }
        }
    }

    private void buttonOneSetup() {
        reminder.setBackground(Color.GREEN);
        reminder.setText("Add Item and Time Operation in Use");
        determineUrgentOrNot();
        reminder.setBackground(Color.red);
        reminder.setText("No Operations Are in Use");

    }

    private void buttonTwoSetup() {
        reminder.setBackground(Color.GREEN);
        reminder.setText("Delete Item Operation in Use");
        buttonDeletePressed();
        reminder.setBackground(Color.red);
        reminder.setText("No Operations Are in Use");
    }

    private void buttonThreeSetup() {
        reminder.setBackground(Color.GREEN);
        reminder.setText("Setup Exam Operation in Use");
        setupExamGui();
        reminder.setBackground(Color.red);
        reminder.setText("No Operations Are in Use");
    }

    private void buttonFourSetup() {
        reminder.setBackground(Color.GREEN);
        reminder.setText("Delete Exam Operation in Use");
        deleteExamGui();
        reminder.setBackground(Color.red);
        reminder.setText("No Operations Are in Use");
    }

    private void buttonFiveButton() {
        reminder.setBackground(Color.GREEN);
        reminder.setText("Get Locations and Time Operation in Use");
        presentLocation();
        reminder.setBackground(Color.red);
        reminder.setText("No Operations Are in Use");
    }

    private void beforeExamUse() {
        console.setText("Stored Exams:"
                + "\n" + erf.formNameList(erf.returnExams()));
    }



    private void buttonOneDual() {
        formatter();
        buttonOneSetup();
    }

    private void buttonTwoDual() {
        formatter();
        buttonTwoSetup();
    }

    private void buttonThreeDual() {
        beforeExamUse();
        buttonThreeSetup();

    }

    private void buttonFourDual() {
        beforeExamUse();
        buttonFourSetup();
    }

    private void buttonFiveDual() {
        beforeExamUse();
        buttonFiveButton();
    }
    //EFFECTS: Takes user input and begins operation

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userinput) {
            buttonOneDual();
        }
        if (e.getSource() == deleteitem) {
            buttonTwoDual();
        }
        if (e.getSource() == exambutton) {
            buttonThreeDual();
        }
        if (e.getSource() == deleteexam) {
            buttonFourDual();
        }
        if (e.getSource() == locationbutton) {
            buttonFiveDual();
        }

    }

    private void presentLocation() {
        String str = JOptionPane.showInputDialog(null, "Type Name of Exam you would like to see Locations and Time:");
        if (str == (null)) {
            console.setText("Stored Exams:"
                    + "\n" + erf.formNameList(erf.returnExams()).toString());
            console.append("\nOperation has been cancelled!");
            return;
        }
        if (!erf.formNameList(erf.returnExams()).contains(str)) {
            console.setText("Stored Exams:"
                    + "\n" + erf.formNameList(erf.returnExams()).toString());
            console.append("\nOperation has been cancelled because exam not found!");
            return;
        }
        console.setText("Locations:");
        console.append("\n" + erf.examStatementLocations(str));
        console.append("\n" + "Time:" + "\n" + erf.getExamByName(str).getListNameDate());


    }

    private void deleteExamGui() {
        String str = JOptionPane.showInputDialog(null, "Type Name of Exam to Delete:");
        if (str == (null)) {
            console.setText("Stored Exams:"
                    + "\n" + erf.formNameList(erf.returnExams()).toString());
            console.append("\nOperation has been cancelled!");
            return;
        }
        Exam dummyexam = new Exam(str);
        erf.removeExam(dummyexam);
        console.setText("Stored Exams:"
                + "\n" + erf.formNameList(erf.returnExams()).toString());

    }


    private void setupExamGui() {
        String examinput = JOptionPane.showInputDialog(null, "Type 1 to add MATH 221."
                + "\nType 2 for CPSC 210");
        if (examinput == (null)) {
            console.setText("Stored Exams:"
                    + "\n" + erf.formNameList(erf.returnExams()).toString());
            console.append("\nOperation has been cancelled!");
            return;
        }
        if (examinput.equals("1")) {
            erf.addFirstExam();
            console.setText("MATH 221 Exam Data has been added!");
            console.append("\n" + erf.formNameList(erf.returnExams()));

        }
        if (examinput.equals("2")) {
            erf.addSecondExam();
            console.setText("CPSC 210 Exam Data has been added!");
            console.append("\n" + erf.formNameList(erf.returnExams()));

        }
    }

    private void determineUrgentOrNot() {
        String deter = JOptionPane.showInputDialog(null, "Type urgent for urgent item."
                + "\nType regular for regular item.");
        if (deter == (null)) {
            todolistOperationCancel();
            return;
        }
        if (deter.equals("regular")) {
            selectedRegularItem();
        }
        if (deter.equals("urgent")) {
            selectedUrgertItem();
        }
    }


    private void selectedUrgertItem() {
        String input = JOptionPane.showInputDialog(null, "Please Enter Item: ");
        if (input == (null)) {
            todolistOperationCancel();
            return;
        }
        String input2 = JOptionPane.showInputDialog(null, "Please Enter Time: ");
        if (input2 == (null)) {
            todolistOperationCancel();
            return;
        }
        try {
            list.addItem(formUrgItem(input, input2));
            formatter();
        } catch (TooLong tooLong) {
            JOptionPane.showMessageDialog(null, "WARNING list is over max of 10, you must delete an Item"
                    + "\nto add new Items! Must Press OK!");
            buttonDeletePressed();
        }
    }

    private UrgentItem formUrgItem(String input, String input2) {
        UrgentItem it = new UrgentItem("", "");
        it.setName(input);
        it.setTime(input2);
        return it;
    }

}






