package model;

import exceptions.TooLong;

import java.util.ArrayList;

public interface Deleteable {
    //Taken from Course

    // MODIFIES: this
    // EFFECTS: removes an allitem (it) from the todolistItems
    // and takes the parameter and stores it in observers
    // and it prints out statistics onto the console.

    void deleteItem(AllItem it);

}
