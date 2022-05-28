package Logicalagent;

import Database.Modelloader;
import Database.Modelsaver;
import Events.*;
import Model.*;
import com.google.gson.Gson;
import utils.Request;
import utils.utilobject;

import java.util.ArrayList;

public class logicalagent {

    Game game;
    User user;
    GameManager gameManager;
    Modelloader modelloader;
    Modelsaver modelsaver;
    Request request;
    PlayGame playGame;
    int ships_destroyed=0;

    public PlayGame getPlayGame() {
        return playGame;
    }

    public void setPlayGame(PlayGame playGame) {
        this.playGame = playGame;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Modelloader getModelloader() {
        return modelloader;
    }

    public void setModelloader(Modelloader modelloader) {
        this.modelloader = modelloader;
    }

    public Modelsaver getModelsaver() {
        return modelsaver;
    }

    public void setModelsaver(Modelsaver modelsaver) {
        this.modelsaver = modelsaver;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGame(Game game) {
        this.game = game;
    }



    public void destroyship(Logicship logicship, ArrayList<ArrayList<Logiccell>> cells )
    {
        ArrayList<utilobject> utilobjects=logicship.getUtilobjects();
        for(int g=0;g<utilobjects.size();g++)
        {
            int i=utilobjects.get(g).getX();
            int j=utilobjects.get(g).getY();


            if(i-1>=0)
                cells.get(i-1).get(j).setIsactive(false);
            if(i+1<=9)
                cells.get(i+1).get(j).setIsactive(false);
            if(j+1<=9)
                cells.get(i).get(j+1).setIsactive(false);
            if(j-1>=0)
                cells.get(i).get(j-1).setIsactive(false);
            if(i+1<=9&&j+1<=9)
                cells.get(i+1).get(j+1).setIsactive(false);
            if(i+1<=9&&j-1>=0)
                cells.get(i+1).get(j-1).setIsactive(false);
            if(i-1>=0&&j-1>=0)
                cells.get(i-1).get(j-1).setIsactive(false);
            if(i-1>=0&&j+1<=9)
                cells.get(i-1).get(j+1).setIsactive(false);
        }



    }

    public void scoreboard()
    {
       ArrayList<User> users=modelloader.getUsers();
       String x="",m="online";
       for(int g=0;g<users.size();g++)
       {
           if(users.get(g).getGamesnumber()>0) {


               if (!users.get(g).isOnline())
                   m = "offline";
               x += users.get(g).getUsername() + "   games number:" + users.get(g).getGamesnumber() + " wins:" + users.get(g).getWins() + " " + m;
               x += "\n";
           }
       }
        scoreevent scoreevent=new scoreevent();
       scoreevent.setScoreboard(x);
       Globalevent globalevent=new Globalevent();
       globalevent.setTitle("scoreboard");
        Gson gson=new Gson();
        globalevent.setGson(gson.toJson(scoreevent, scoreevent.class));
        request.getGlobalevents().add(globalevent);
    }


    public void bomb(int i,int j) {
        int x=0;
        int y=0;

        if (game.getUsers().get(game.getTurn()-1).equals(user)&&!game.isIsfinished()) {
            ArrayList<ArrayList<Logiccell>> cells = new ArrayList<>();
            if (game.getTurn() == 1)
                cells = game.getCells2();
            else
                cells = game.getCells1();
            if (cells.get(i).get(j).isIsactive()) {
                cells.get(i).get(j).setIsactive(false);
                if (cells.get(i).get(j).getLogicship() != null) {
                    y = 1;

                    cells.get(i).get(j).getLogicship().setSize(cells.get(i).get(j).getLogicship().getSize() - 1);
                    if (cells.get(i).get(j).getLogicship().getSize() == 0) {
                        ships_destroyed++;
                        destroyship(cells.get(i).get(j).getLogicship(), cells);
                        if(game.getTurn()==1)
                            game.setShips2(game.getShips2()-1);
                        else {
                            game.setShips1(game.getShips1()-1);
                        }
                    }
                    if(ships_destroyed==10)
                    {
                        System.out.println(ships_destroyed);
                        game.setIsfinished(true);
                        gameManager.finishgame(game,user);
                    }
                }


                if (game.getTurn() == 2) {
                    game.setCells1(cells);
                    if (y == 0)
                        game.setTurn(1);
                } else {
                    game.setCells2(cells);
                    if (y == 0)
                        game.setTurn(2);
                }
                playGame.setChangeturn(playGame.getChangeturn()+1);

            }


        }
    }
    public void finish()
    {
        game.setIsfinished(true);
        User user=game.getUsers().get(0);
        if(user.getUsername().equals(this.user.getUsername()))
            user=game.getUsers().get(1);
        gameManager.finishgame(game,user);
    }



    public void login(loginevent loginevent)
    {
             User user=modelloader.getuserbyusername(loginevent.getUsername());
             if(user!=null&&user.getPasword().equals(loginevent.getPassword()))
             {
                 user.setOnline(true);
                 this.user=user;
                 Globalevent globalevent=new Globalevent();
                 globalevent.setTitle("canlogin");
                 globalevent.setGson("...");
                 request.getGlobalevents().add(globalevent);

             }
             else
             {
                 Globalevent globalevent=new Globalevent();
                 globalevent.setTitle("cantlogin");
                 globalevent.setGson("...");
                 request.getGlobalevents().add(globalevent);
             }
    }
    public void signup(signupevent signupevent)
    {
        User user=modelloader.getuserbyusername(signupevent.getUsername());
        if(user==null)
        {
            user=new User();
            user.setOnline(true);
            user.setUsername(signupevent.getUsername());
            user.setPasword(signupevent.getPassword());
            this.user=user;
            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("canregister");
            globalevent.setGson("...");
            request.getGlobalevents().add(globalevent);
            Modelsaver.saveuser(user);
        }
        else
        {
            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("cantregister");
            globalevent.setGson("...");
            request.getGlobalevents().add(globalevent);
        }

    }
    public void set_offline()
    {
        user.setOnline(false);
        Modelsaver.saveuser(user);
    }

    public void newgame()
    {
        gameManager.newgame(this);
    }


         public void start_watch()
         {
             gameManager.choose_game_watch(request);
         }
         public void choose(int i)
         {
             gameManager.startwatch(request,i);
         }
        public void finish_watch(int i)
            {
                   gameManager.finish_watch(i,request);
            }

        public void show_info()
        {
            infoevent infoevent=new infoevent();
            infoevent.setUsername(user.getUsername());
            infoevent.setGames(user.getGamesnumber());
            infoevent.setWins(user.getWins());



            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("show_info");
            Gson gson=new Gson();
            globalevent.setGson(gson.toJson(infoevent, infoevent.class));
            request.getGlobalevents().add(globalevent);

        }
        public void delete()
        {
            Modelsaver.delete_user(user);
            modelloader.getUsers().remove(user);
        }




    }







