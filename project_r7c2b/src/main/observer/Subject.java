package observer;

import model.AllItem;


import java.util.ArrayList;
import java.util.List;

public class Subject {
    ArrayList<String> list;
    private List<Observer> observers = new ArrayList<>();
    int count;

    //MODIFIES: this
    //EFFECTS: adds an observer (obs) to the observers list observers

    public void addObserver(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
        }
    }

    //REQUIRES: One Observer/Allitem obs inside the list observers
    //EFFECTS: Prints statistics to the console
    public void notifyObserver(ArrayList<AllItem> tl) {
        observers.get(0).update(tl);
        list = new ArrayList<>();
        count = 0;
        for (Observer ob : observers) {
            list.add(ob.getName());
            count++;
        }
        System.out.println("Deleted Items:");
        System.out.println(list);
        String strcount = Integer.toString(count);
        System.out.println("Number of Items Deleted: " + strcount);

    }

    public List<Observer> returnObserverList() {
        return observers;
    }

    public Integer returnCount() {
        return count;
    }

    public ArrayList<String> returnStringList() {
        return list;
    }

    //REQUIRES: One Observer/Allitem inside the list observers
    //EFFECTS: Return the string of the statistics
    public String notifyObserverGui(ArrayList<AllItem> tl) {
        list = new ArrayList<>();
        count = 0;
        for (Observer ob : observers) {
            list.add(ob.getName());
            count++;
        }
        String strcount = Integer.toString(count);
        return  observers.get(0).update2(tl) + "\n"
                + "Number of Items Deleted: " + strcount + "\n"
                + "Deleted Items:" + "\n"
                + list.toString();


    }
}
