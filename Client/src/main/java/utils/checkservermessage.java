package utils;

import Controller.*;
import Events.*;
import com.google.gson.Gson;

public class checkservermessage {
    public static
    void check(Globalevent globalevent,Request request)
    {

        if(globalevent!=null&&globalevent.getTitle()!=null)
        {

        if(!globalevent.getTitle().equals("nothing")) {
            if (globalevent.getTitle().equals("canlogin"))///////handle connection
            {
                loginpage loginpagee = (loginpage) request.getCurrentpage();
                loginpagee.canlogin();
            }
            if (globalevent.getTitle().equals("cantlogin")) {
                loginpage loginpagee = (loginpage) request.getCurrentpage();
                loginpagee.cantlogin();
            }
            if (globalevent.getTitle().equals("canregister")) {
                Registerpage signup = (Registerpage) request.getCurrentpage();
                signup.canregister();
            }
            if (globalevent.getTitle().equals("cantregister")) {
                Registerpage signup = (Registerpage) request.getCurrentpage();
                signup.cantregister();
            }
            if (globalevent.getTitle().equals("newborad")) {

            }
            if (globalevent.getTitle().equals("choosegametowatch")) {

            }
            if (globalevent.getTitle().equals("startgame")) {
                Gson gson=new Gson();
                gameevent gameevent=gson.fromJson(globalevent.getGson(),gameevent.class);
                Waitingpage waitingpage=(Waitingpage) request.getCurrentpage();
                waitingpage.start(gameevent);


            }
            if(globalevent.getTitle().equals("gameinfo"))
            {
                Gson gson=new Gson();
                gameevent gameevent=gson.fromJson(globalevent.getGson(),gameevent.class);
                gameboard waitingpage=(gameboard) request.getCurrentpage();
                waitingpage.makeboard(gameevent);
            }
            if(globalevent.getTitle().equals("no_game_for_watch"))
            {

            }
            if(globalevent.getTitle().equals("watching"))
            {
                Gson gson=new Gson();
                watchevent watchevent=gson.fromJson(globalevent.getGson(),watchevent.class);
                watchingpage watchingpage=(watchingpage) request.getCurrentpage();
                watchingpage.makecell(watchevent);
                System.out.println("in-watching");
            }
            if(globalevent.getTitle().equals("finish-gameplay"))
            {
                Gson gson=new Gson();
                finishgameevent gameevent=gson.fromJson(globalevent.getGson(),finishgameevent.class);
                    gameboard gameboard=(gameboard) request.getCurrentpage();
                    gameboard.scoreboard(gameevent);

            }
            if(globalevent.getTitle().equals("scoreboard"))
            {
                Gson gson=new Gson();
                scoreevent scoreevent=gson.fromJson(globalevent.getGson(),scoreevent.class);
                scoreboard scoreboard=(scoreboard) request.getCurrentpage();
                scoreboard.initialize(scoreevent);

            }
            if(globalevent.getTitle().equals("finish-game-watch"))
            {
                Gson gson=new Gson();
                finishgameevent gameevent=gson.fromJson(globalevent.getGson(),finishgameevent.class);
                watchingpage watchingpage=(watchingpage) request.getCurrentpage();
                watchingpage.scoreboard(gameevent);

            }
            if(globalevent.getTitle().equals("show_info"))
            {
                Gson gson=new Gson();
                infoevent infoevent=gson.fromJson(globalevent.getGson(),infoevent.class);
                infopage infopage=(infopage) request.getCurrentpage();
                infopage.show_info(infoevent);

            }
            if(globalevent.getTitle().equals("choose_game"))
            {
                Gson gson=new Gson();
                choosegameevent infoevent=gson.fromJson(globalevent.getGson(),choosegameevent.class);
                choosegame infopage=(choosegame) request.getCurrentpage();
                infopage.initializee(infoevent);
                System.out.println("im in");

            }


        }




        }
    }
}
