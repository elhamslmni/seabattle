package Events;

import Model.Logiccell;

import java.util.ArrayList;

public class watchevent {
    String user1,user2;
    ArrayList<ArrayList<Logiccell>> cells1;
    ArrayList<ArrayList<Logiccell>> cells2;
    int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public ArrayList<ArrayList<Logiccell>> getCells1() {
        return cells1;
    }

    public void setCells1(ArrayList<ArrayList<Logiccell>> cells1) {
        this.cells1 = cells1;
    }

    public ArrayList<ArrayList<Logiccell>> getCells2() {
        return cells2;
    }

    public void setCells2(ArrayList<ArrayList<Logiccell>> cells2) {
        this.cells2 = cells2;
    }
}
