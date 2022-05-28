package Database;

import Model.Game;
import Model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Modelloader {

    ArrayList<User> users=new ArrayList<>();
    ArrayList<Game> games=new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void loadallusers()
    {
        File file=new File("src/database/Users");
        Gson gson= new Gson();
        File[] f=file.listFiles();
        for (File p:
                f) {
            try {
                Scanner scanner=new Scanner(p);
                User user=gson.fromJson(scanner.nextLine(),User.class);
                users.add(user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
    public void loadallGames()
    {
        File file=new File("src/database/Games");
        Gson gson= new Gson();
        File[] f=file.listFiles();
        for (File p:
                f) {
            try {
                Scanner scanner=new Scanner(p);
                Game game=gson.fromJson(scanner.nextLine(),Game.class);
                games.add(game);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public User getuserbyusername(String username)
    {
        for(int g=0;g<users.size();g++)
            if(users.get(g).getUsername().equals(username))
                return users.get(g);


        return null;
    }
}
