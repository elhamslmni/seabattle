import Database.Modelloader;
import Database.Modelsaver;


public class Main {
    public static void main(String[] args) {
        Modelloader modelloader=new Modelloader();
        Modelsaver modelsaver=new Modelsaver();
       socketsjoin socketsjoin=new socketsjoin(modelsaver,modelloader);
       socketsjoin.start();
    }
}
