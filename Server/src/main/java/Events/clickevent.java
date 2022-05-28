package Events;

public class clickevent extends event{

    int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void visit()
    {
        System.out.println(i);
    }
}
