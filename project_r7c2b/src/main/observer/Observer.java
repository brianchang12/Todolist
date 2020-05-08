package observer;

import model.AllItem;
import model.TodoList;

import java.util.ArrayList;

public interface Observer {

    public void update(ArrayList<AllItem> list);

    public String update2(ArrayList<AllItem> list);

    String getName();
}
