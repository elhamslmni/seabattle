package Database;

import Model.Game;
import Model.User;
import com.google.gson.Gson;

import java.io.*;

public class Modelsaver {

    public static void saveuser(User user)
    {
        Gson gson=new Gson();
        File file=new File("src/database/Users/"+user.getUsername());
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream(file,false));
            printStream.println(gson.toJson(user));
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public  void savegame(Game game)
    {
        Gson gson=new Gson();
        File file=new File("src/database/Games/"+game.getId());
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream(file,false));
            printStream.println(gson.toJson(game));
            printStream.flush();
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public  static  void  delete_user(User user)
    {
        File file=new File("src/database/Users/"+user.getUsername());
        if(file.exists()) {
                file.delete();
        }
    }
}
