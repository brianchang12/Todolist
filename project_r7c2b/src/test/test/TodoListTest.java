package test;


import exceptions.TooLong;
import model.AllItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.TodoList.loadFile;
import static org.junit.jupiter.api.Assertions.*;


import model.Item;
import model.TodoList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class TodoListTest {
    TodoList tdl1;
    TodoList tdl2;
    ArrayList<AllItem> array1;
    Item it1;
    Item it2;
    Item it3;


    @BeforeEach
    public void setup() {
        tdl1 = new TodoList();
        tdl2 = new TodoList();
        array1 = new ArrayList<AllItem>();
        it1 = new Item("Lunch", "12:00");
        it2 = new Item("Homework", "9:00");
        it3 = new Item("Sleep", "10:00");


    }

    @Test
    public void testSave() throws FileNotFoundException {
        File file1 = new File("./data/something.txt");
        PrintWriter writing = new PrintWriter(file1);
        writing.println(it1.getName());
        writing.println(it1.getTime());
        writing.close();
        array1.add(it1);
        tdl1.saveFile(array1);
        assertEquals(file1, tdl1.saveFile(array1));
    }

    @Test
    public void testLoad() throws FileNotFoundException {
        array1.add(it1);
        array1.add(it2);
        tdl1.saveFile(array1);
        assertEquals("Lunch", loadFile().get(0).getName());
        assertEquals("Homework", loadFile().get(1).getName());

    }



    @Test
    public void testCheckItems() throws TooLong {
        tdl1.addItem(it1);
        tdl1.addItem(it2);
        ArrayList<String> dummy = new ArrayList<>();
        dummy.add(it1.getName());
        dummy.add(it2.getName());
        array1.add(it1);
        array1.add(it2);
        assertEquals(dummy,tdl1.splitItems(array1));

    }

    @Test
    public void testCheckTimes() throws TooLong {
        tdl1.addItem(it1);
        tdl1.addItem(it2);
        ArrayList<String> dummy = new ArrayList<>();
        dummy.add(it1.getTime());
        dummy.add(it2.getTime());
        array1.add(it1);
        array1.add(it2);
        assertEquals(dummy,tdl1.splitTimes(array1));

    }


    @Test
    public void testAddItem() throws TooLong {
        tdl1.addItem(it1);
        tdl1.addItem(it2);
        tdl2.addItem(it1);
        tdl2.addItem(it2);
        assertEquals(tdl2.getList(), tdl1.getList());

    }

    @Test
    public void testDeleteItem() throws TooLong {
        tdl1.addItem(it1);
        tdl1.addItem(it2);
        tdl1.deleteItem(it2);
        tdl2.addItem(it1);
        assertEquals(tdl2.getList(), tdl1.getList());

    }

    @Test
    public void testGetName() {
        assertEquals("Lunch", it1.getName());

    }

    @Test
    public void testGetTime() {
        assertEquals("12:00", it1.getTime());

    }

    @Test
    public void testTooLongFail() {
        try {
             tdl1.addItem(it1);
             tdl1.getList();
        } catch (TooLong tooLong) {
            fail("Not too long");
        }
        assertEquals(1 , tdl1.getList().size());
    }



    @Test
    public void testTooLongPass() {
        try {
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);
            tdl1.addItem(it1);

        } catch (TooLong tooLong) {
        }
        assertEquals(10, tdl1.getList().size());
    }


}