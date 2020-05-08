package model;

public class UrgentItem extends AllItem {


    public UrgentItem(String name, String time) {
        super(name, time);

    }

    public void setName(String name) {
        this.name = name + "-urgent";
    }
}
