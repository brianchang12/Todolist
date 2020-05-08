package test;

import model.AllItem;
import model.Item;
import model.UrgentItem;
import observer.Observer;
import observer.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {
    List<Observer> observers;
    Observer ob1;
    Observer ob2;
    Observer ob3;
    Subject sub;
    ArrayList<AllItem> al;
    Item it1;
    UrgentItem it2;

    @BeforeEach
    public void setup() {
        ob1 = new Item("eat", "12");
        ob2 = new Item("cook", "10");
        ob3 = new Item("sleep", "4");
        sub = new Subject();
        observers = new ArrayList<>();
        al = new ArrayList<>();
        it1 = new Item("eat", "12");
        it2 = new UrgentItem("cook", "10");
    }

    @Test
    public void testAddObservers() {
        sub.addObserver(ob1);
        observers.add(ob1);
        assertEquals(observers,sub.returnObserverList());
        sub.addObserver(ob1);
        assertEquals(observers, sub.returnObserverList());

    }

    @Test
    public void testNotifyObservers() {
        al.add(it1);
        al.add(it2);
        sub.addObserver(ob1);
        sub.addObserver(ob2);
        sub.notifyObserver(al);
        assertEquals(2,sub.returnCount());
        ArrayList<String> strings = new ArrayList<>();
        strings.add("eat");
        strings.add("cook");
        assertEquals(strings ,sub.returnStringList());

    }
    @Test
    public void testNotifyObserverGui() {
        al.add(it1);
        al.add(it2);
        sub.addObserver(ob1);
        sub.addObserver(ob2);
        sub.notifyObserverGui(al);
        assertEquals( "Items left uncompleted: 2"
        + "\n" + "Number of Items Deleted: 2"
        + "\n" + "Deleted Items:"
        + "\n" + sub.returnStringList(),sub.notifyObserverGui(al));

    }


}
