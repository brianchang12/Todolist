package model;

import java.util.ArrayList;
import java.util.List;

public interface Splitable {
    //Taken from Course

    //REQUIRES: todolistItems to have one or more items
    //EFFECTS: Extract the names of allitems from a todolistItems
    // and returns a list of strings of names of items

    List<String> splitItems(ArrayList<AllItem> tdl);

    //REQUIRES: todolistItems to have one or more items
    //EFFECTS: Extract times of allitems from a todolistItems and returns a list of string of times of items


    List<String> splitTimes(ArrayList<AllItem> tdl);
}
