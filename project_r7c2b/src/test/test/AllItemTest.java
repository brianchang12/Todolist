package test;

import model.AllItem;
import model.Item;
import model.UrgentItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class AllItemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    Item it1;
    UrgentItem it2;
    AllItem it3;


    @BeforeEach
    public void setup() {
        it1 = new Item("","");
        it2 = new UrgentItem("","");
        it1.setName("eat");
        it1.setTime("12");
        it2.setName("read");
        it2.setTime("1");
        it3 = new Item("","");
        it3.setName("eat");
        it3.setTime("12");
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));


    }

    @Test
    public void testUpdate2() {
        ArrayList<AllItem> alt = new ArrayList<>();
        alt.add(it1);
        assertEquals("Items left uncompleted: 1",it3.update2(alt));
    }





    @Test
    public void testUpdate() {
        ArrayList<AllItem> alt = new ArrayList<>();
        alt.add(it1);
        it3.update(alt);
        assertEquals("Items left uncompleted: 1\n", outContent.toString());
    }
    @Test
    public void testGetTime() {

        assertEquals("12", it1.getTime());
        assertEquals("1", it2.getTime());
        assertEquals("12",it3.getTime());
    }

    @Test
    public void testGetName() {

        assertEquals("eat", it1.getName());
        assertEquals("read-urgent", it2.getName());
        assertEquals("eat",it3.getName());

    }
}