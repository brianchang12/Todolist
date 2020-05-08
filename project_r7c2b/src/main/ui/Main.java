package ui;

import exceptions.TooLong;
import model.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

import static network.API.obtainInfo;
import static network.API.produceStart;

//Stackoverflow
//https://stackoverflow.com/questions/11871520/how-can-i-read-input-from-the-console-using-the-scanner-class-in-java
//https://stackoverflow.com/questions/5287538/how-can-i-get-the-user-input-in-java

public class Main {
    int choice1 = 0;
    int choice2 = 0;
    int choice3 = 0;


    private Scanner inputMain = new Scanner(System.in);
    ExamReminderFunctionality erf;
    TodoList list;
    private AllItem oneItemv2;
    private AllItem oneItem;


    public Main() {
        erf = new ExamReminderFunctionality();
        list = new TodoList();
    }

    //Runs Program

    public static void main(String[] args) throws IOException, ParseException {
        Main m = new Main();
        obtainInfo();
        produceStart();
        options();
        m.menu();

    }

    private void deleteItemSetup() {
        System.out.println("Type the name of the exam to delete");
        Scanner inputdelete = new Scanner(System.in);
        String rid = inputdelete.nextLine();
        Exam dummyexam = new Exam(rid);
        (erf.returnExams()).remove(dummyexam);
        options();
        choiceTwoShowItem();
    }




    private void choiceOneAddItem() throws TooLong {
        int choiceadd;
        Scanner inputreg = new Scanner(System.in);
        System.out.println("Would you like to add a Regular item or Urgent item? Type 1 for Reg, any int for Urg");
        choiceadd = inputreg.nextInt();
        if (choiceadd == 1) {
            addRegItemToConsole();
            options();
        } else {
            addUrgItemToConsole();
            options();
        }


    }


    private void choiceThreeDeleteItem() {
        Scanner inputDeleteItem = new Scanner(System.in);
        System.out.println("please enter the name and time of the item you want to delete");
        String deleteNamePlusTime = inputDeleteItem.nextLine();
        for (int i = 0; list.getList().size() > i; i++) {
            if (list.getList().get(i).getName().equals(deleteNamePlusTime) == true) {
                list.deleteItem(list.getList().get(i));
            }

        }
        System.out.println("Deleted Successfully");
        options();
        choiceTwoShowItem();

    }


    private void choiceTwoShowItem() {
        System.out.println(list.splitItems(list.getList()));
        System.out.println(list.splitTimes(list.getList()));
    }


    private static void options() {
        System.out.println("Type 1 to add a new item to your to do list.");
        System.out.println("Type 2 delete item from list");
        System.out.println("Type 3 to setup exam list or use features of exam list");


    }

    // taken from StackOverflow
    private void addRegItemToConsole() throws TooLong {
        Scanner input2 = new Scanner(System.in);
        String nameOnly = input2.nextLine();
        String timeOnly = input2.nextLine();
        oneItem = new Item("", "");
        oneItem.setName(nameOnly);
        oneItem.setTime(timeOnly);
        list.addItem(oneItem);
    }


    private void addUrgItemToConsole() throws TooLong {
        Scanner input2 = new Scanner(System.in);
        String nameOnly = input2.nextLine();
        String timeOnly = input2.nextLine();
        oneItemv2 = new UrgentItem("", "");
        oneItemv2.setName(nameOnly);
        oneItemv2.setTime(timeOnly);
        list.addItem(oneItemv2);
    }

    private void examSetupOrUse() {
        examSetupAddExam();
        int choiceexamsetup;
        if (choice2 == 1) {
            setupExam();
        } else {
            System.out.println(erf.formNameList(erf.returnExams()));
            System.out.println("Type 1 to see locations and any num to delete exam");
            Scanner inputDecidelocationorDeleteexam = new Scanner(System.in);
            choiceexamsetup = inputDecidelocationorDeleteexam.nextInt();
            if (choiceexamsetup == 1) {
                setupLocation();
            } else {
                deleteItemSetup();
            }
        }
    }

    // taken from StackOverflow
    private void setupExam() {
        Scanner input4 = new Scanner(System.in);
        while (choice3 != 4) {
            System.out.println("Type (1) MATH 221, (2) CPSC 210, (3) Stop");
            choice3 = input4.nextInt();
            if (choice3 == 1) {
                erf.addFirstExam();
            }
            if (choice3 == 2) {
                erf.addSecondExam();
            }
            if (choice3 == 3) {
                break;
            }
        }
        System.out.println("Set up is finished");
        options();
        choiceTwoShowItem();
    }


    private void examSetupAddExam() {
        Scanner inputstart = new Scanner(System.in);
        System.out.println("Type 1 to begin setup, type any num to use features and see exam list");
        choice2 = inputstart.nextInt();
    }


    private void setupLocation() {
        System.out.println("Type the name of the exam you want to know the location of");
        Scanner inputSeeLocation = new Scanner(System.in);
        String examnames = inputSeeLocation.nextLine();
        System.out.println(erf.examStatementLocations(examnames));
        options();
        choiceTwoShowItem();
    }



    private void exceptionCatcher() {
        try {
            choiceOneAddItem();
        } catch (TooLong tooLong) {
            choiceThreeDeleteItem();
        } finally {
            choiceTwoShowItem();
        }
    }

   //EFFECTS: takes user input and begins the operation

    public void menu() {
        while (choice1 != 4) {
            choice1 = inputMain.nextInt();
            if (choice1 == 1) {
                exceptionCatcher();
            }
            if (choice1 == 2) {
                choiceThreeDeleteItem();
            }
            if (choice1 == 3) {
                examSetupOrUse();
            }

        }
    }


}







