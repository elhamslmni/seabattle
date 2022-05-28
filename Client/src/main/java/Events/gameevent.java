package Events;

import Model.Logiccell;

import java.util.ArrayList;

public class gameevent {

    String id;
    ArrayList<ArrayList<Logiccell>> cells1;
    ArrayList<ArrayList<Logiccell>> cells2;
    String username;
    int turn;
    int usernumber;
    long time;
    int ships1=10;
    int ships2=10;

    public int getShips1() {
        return ships1;
    }

    public void setShips1(int ships1) {
        this.ships1 = ships1;
    }

    public int getShips2() {
        return ships2;
    }

    public void setShips2(int ships2) {
        this.ships2 = ships2;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(int usernumber) {
        this.usernumber = usernumber;
    }
}
