package Model;

import java.util.ArrayList;

public class Game {

    String id;
    ArrayList<ArrayList<Logiccell>> cells1;
    ArrayList<ArrayList<Logiccell>> cells2;
    ArrayList<User> users;
    int turn=1;
    int i;
    boolean isfinished=false;
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

    public boolean isIsfinished() {
        return isfinished;
    }

    public void setIsfinished(boolean isfinished) {
        this.isfinished = isfinished;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Game(User user1, User user2)
    {
        cells1=new ArrayList<>();
        cells2=new ArrayList<>();
        users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
