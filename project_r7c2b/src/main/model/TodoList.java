package model;

import exceptions.TooLong;
import observer.Subject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

// taken from StackOverflow,Oracle
// https://stackoverflow.com/questions/15695984/java-print-contents-of-text-file-to-screen
// https://docs.oracle.com/javase/7/docs/api/java/io/PrintWriter.html

public class TodoList extends Subject implements Deleteable, Splitable {

    public ArrayList<AllItem> todoListItems;


    public TodoList() {
        todoListItems = new ArrayList<AllItem>();

    }

    // MODIFIES: this
    // EFFECTS: Adds an allitem (it) to the todolistItems,
    // once 10 items are in the todolistItems
    // and method is called again the TooLong exception will be thrown.
    public void addItem(AllItem it) throws TooLong {
        if (todoListItems.size() >= 10) {
            throw new TooLong();
        } else {
            todoListItems.add(it);

        }
    }


    // MODIFIES: this
    // EFFECTS: removes an allitem (it) from the todolistItems
    // and takes the parameter and stores it in observers
    // and it prints out statistics onto the console.
    @Override
    public void deleteItem(AllItem it) {
        todoListItems.remove(it);
        addObserver(it);
        notifyObserver(todoListItems);
    }


    //EFFECTS: return todolistItems
    public ArrayList<AllItem> getList() {
        return todoListItems;
    }


    //REQUIRES: todolistItems to have one or more items
    //EFFECTS: Extract the names of allitems from a todolistItems
    // and returns a list of strings of names of items
    public List<String> splitItems(ArrayList<AllItem> todoListItems) {
        ArrayList<String> itemNames = new ArrayList<String>();
        this.todoListItems = todoListItems;
        for (int i = 0; i < todoListItems.size(); i++) {
            itemNames.add(todoListItems.get(i).getName());
        }
        return itemNames;
    }


    //REQUIRES: todolistItems to have one or more items
    //EFFECTS: Extract times of allitems from todolistItems and returns a list of string of times of items
    public List<String> splitTimes(ArrayList<AllItem> todoListItems) {
        ArrayList<String> itemTimes = new ArrayList<String>();
        this.todoListItems = todoListItems;
        for (int i = 0; i < todoListItems.size(); i++) {
            itemTimes.add(todoListItems.get(i).getTime());
        }
        return itemTimes;
    }

    //EFFECTS: Creates a new file and writes the items of (items) to said file
    public static File saveFile(ArrayList<AllItem> items) throws FileNotFoundException {
        File file = new File("./data/something.txt");
        PrintWriter print1 = new PrintWriter(file);
        for (int i = 0; items.size() > i; i++) {
            print1.println(items.get(i).getName());
            print1.println(items.get(i).getTime());
        }
        print1.close();
        return file;
    }


    //EFFECTS: Creates and returns Arraylist<Allitem> (itemlist) from the lines from the file
    public static ArrayList<AllItem> loadFile() throws FileNotFoundException {
        AllItem newItem;
        File file = new File("./data/something.txt");
        String namepart;
        String timepart;
        ArrayList<AllItem> itemlist = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            namepart = scanner.nextLine();
            timepart = scanner.nextLine();
            newItem = new Item(namepart, timepart);
            itemlist.add(newItem);
        }
        return itemlist;

    }
}

