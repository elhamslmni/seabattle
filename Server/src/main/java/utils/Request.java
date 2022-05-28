package utils;

import Events.Globalevent;

import java.util.ArrayList;

public class Request {

    ArrayList<Globalevent> globalevents;
    public Request()
    {

        globalevents=new ArrayList<>();
    }


    public ArrayList<Globalevent> getGlobalevents() {
        return globalevents;
    }

    public void setGlobalevents(ArrayList<Globalevent> globalevents) {
        this.globalevents = globalevents;
    }
}
