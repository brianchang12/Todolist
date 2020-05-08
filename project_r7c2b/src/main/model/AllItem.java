package model;

import observer.Observer;

import java.util.ArrayList;


//Taken from Course
public abstract class AllItem implements Observer {
    String name;
    String time;



    public AllItem(String name, String time) {
    }


    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    //EFFECTS: returns a string that includes information
    // of number of allitems uncompleted;

    public String update2(ArrayList<AllItem> list) {
        int size = list.size();
        String str = Integer.toString(size);
        return "Items left uncompleted: " + str;
    }


    public void setTime(String time1) {
        this.time = time1;
    }

    //EFFECTS: prints a line that includes information
    // of number of allitems uncompleted;

    public void update(ArrayList<AllItem> tl) {
        int size = tl.size();
        String str = Integer.toString(size);
        System.out.println("Items left uncompleted: " + str);

    }

    public abstract void setName(String name);

}
