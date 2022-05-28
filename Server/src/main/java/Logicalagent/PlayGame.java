package Logicalagent;

import Events.Globalevent;
import Events.gameevent;
import Events.watchevent;
import Model.Game;
import com.google.gson.Gson;
import utils.Request;

import java.time.LocalTime;
import java.util.ArrayList;

public class PlayGame extends Thread{
    Request request1;
    Request request2;
    Game game;
    String user1,user2;

    ArrayList<Request> watchers;
    int i;
    int changeturn1=1;
    int changeturn=1;
    long localTime=System.currentTimeMillis();
    int exit=1;

    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public Request getRequest1() {
        return request1;
    }

    public void setRequest1(Request request1) {
        this.request1 = request1;
    }

    public Request getRequest2() {
        return request2;
    }

    public void setRequest2(Request request2) {
        this.request2 = request2;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getChangeturn1() {
        return changeturn1;
    }

    public void setChangeturn1(int changeturn1) {
        this.changeturn1 = changeturn1;
    }

    public long getLocalTime() {
        return localTime;
    }

    public void setLocalTime(long localTime) {
        this.localTime = localTime;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getChangeturn() {
        return changeturn;
    }

    public void setChangeturn(int changeturn) {
        this.changeturn = changeturn;
    }

    public PlayGame(Request request1, Request request2, Game game)
    {
        this.game=game;
        this.request1=request1;
        this.request2=request2;
        watchers=new ArrayList<>();
    }

    public ArrayList<Request> getWatchers() {
        return watchers;
    }

    public void setWatchers(ArrayList<Request> watchers) {
        this.watchers = watchers;
    }

    public void run()
    {
        Globalevent globalevent=new Globalevent();
        Globalevent globalevent1=new Globalevent();
        watchevent watchevent=new watchevent();
        gameevent gameevent=new gameevent();
        Gson gson=new Gson();
        globalevent.setTitle("gameinfo");
        user1=game.getUsers().get(0).getUsername();
        user2=game.getUsers().get(1).getUsername();
        while (true)
        {
            globalevent.setTitle("gameinfo");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(exit==0)
                break;
            Long time=System.currentTimeMillis();
            gameevent.setTime(time-localTime);
            gameevent.setTurn(game.getTurn());
            gameevent.setCells1(game.getCells1());
            gameevent.setCells2(game.getCells2());
            gameevent.setShips1(game.getShips1());
            gameevent.setShips2(game.getShips2());
            globalevent.setGson(gson.toJson(gameevent,gameevent.class));
            request2.getGlobalevents().add(globalevent);
            request1.getGlobalevents().add(globalevent);
            if(changeturn!=changeturn1)
            {
                localTime=System.currentTimeMillis();
                changeturn1=changeturn;
            }
            if(time-localTime>26000)
            {
                if(game.getTurn()==2)
                    game.setTurn(1);
                else game.setTurn(2);
               localTime=time;
            }



            watchevent.setUser1(user1);
            watchevent.setUser2(user2);
            watchevent.setCells1(game.getCells1());
            watchevent.setCells2(game.getCells2());
            watchevent.setI(i);
            globalevent1.setGson(gson.toJson(watchevent,watchevent.class));
            globalevent1.setTitle("watching");
            for(int i=0;i<watchers.size();i++) {
                System.out.println("watcherssssss");
                watchers.get(i).getGlobalevents().add(globalevent1);
            }

        }



    }




}
