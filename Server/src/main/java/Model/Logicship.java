package Model;

import utils.utilobject;

import java.util.ArrayList;

public class Logicship {
    ArrayList<utilobject> utilobjects;
    int size=0;

    public Logicship(){
        utilobjects=new ArrayList<>();
    }
    public ArrayList<utilobject> getUtilobjects() {
        return utilobjects;
    }

    public void setUtilobjects(ArrayList<utilobject> utilobjects) {
        this.utilobjects = utilobjects;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void addutil(utilobject utilobject){
        utilobjects.add(utilobject);
    }
}
