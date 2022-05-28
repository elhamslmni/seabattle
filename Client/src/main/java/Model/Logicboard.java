package Model;

import java.util.ArrayList;

public class Logicboard {
    ArrayList<ArrayList<Logiccell>> cells;
    ArrayList<Logicship> ships;


    public ArrayList<ArrayList<Logiccell>> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ArrayList<Logiccell>> cells) {
        this.cells = cells;
    }

    public ArrayList<Logicship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Logicship> ships) {
        this.ships = ships;
    }


    public void initialize()
    {





        ///handle ship
        ships=new ArrayList<>();





        for(int g=0;g<10;g++)
            cells.add(new ArrayList<>());


        ////

        for(int i=0;i<10;i++)
            for(int g=0;g<10;g++)
            {
                Logiccell logiccell=new Logiccell();
                logiccell.setI(i);
                logiccell.setJ(g);
                ////chek ship
            }
    }




}
