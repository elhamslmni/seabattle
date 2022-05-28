package utils;

import Controller.Controller;
import Events.Globalevent;

import java.util.ArrayList;

public class Request {

    ArrayList<Globalevent> globalevents;
    Controller currentpage;
    public Request()
    {
        currentpage=new Controller();
        globalevents=new ArrayList<>();
    }

    public Controller getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(Controller currentpage) {
        this.currentpage = currentpage;
    }

    public ArrayList<Globalevent> getGlobalevents() {
        return globalevents;
    }

    public void setGlobalevents(ArrayList<Globalevent> globalevents) {
        this.globalevents = globalevents;
    }
}
