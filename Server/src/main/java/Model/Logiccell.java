package Model;

public class Logiccell {
    int i, j;
    Logicship logicship;
    boolean isactive;
    public Logiccell()
    {
        isactive=true;
        logicship=null;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Logicship getLogicship() {
        return logicship;
    }

    public void setLogicship(Logicship logicship) {
        this.logicship = logicship;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
