package utils;

import Events.*;
import Logicalagent.logicalagent;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.Scanner;

public class inputlistener extends  Thread{

    Scanner scanner;
    PrintWriter printWriter;
    logicalagent logicalagent;
    public inputlistener(Scanner scanner,PrintWriter printWriter,logicalagent logicalagent)
    {
        this.scanner=scanner;
        this.printWriter=printWriter;
        this.logicalagent=logicalagent;
    }


    public void run()
    {
        Gson gson=new Gson();
        String s="nothing",s2="";
        Globalevent globalevent=null;
        while (true)
        {
            s=scanner.nextLine();
            globalevent =gson.fromJson(s,Globalevent.class);
            analiese(globalevent.getTitle(),globalevent.getGson(),gson);
            if(logicalagent.getRequest().getGlobalevents().size()>0)
            {
                s=gson.toJson(logicalagent.getRequest().globalevents.remove(0),Globalevent.class);
                printWriter.println(s);
                printWriter.flush();
            }
            else
            {
                globalevent=new Globalevent();
                globalevent.setGson("...");
                globalevent.setTitle("nothing");
                s=gson.toJson(globalevent,Globalevent.class);
                printWriter.println(s);
                printWriter.flush();
            }

        }
    }

    public void analiese(String title ,String gson,Gson g)
    {
        if(title!=null) {

            if (title.equals("login")) {
                loginevent logineventt = g.fromJson(gson, loginevent.class);
                logicalagent.login(logineventt);
            }
            if (title.equals("signup")) {
                signupevent signupevent = g.fromJson(gson, signupevent.class);
                logicalagent.signup(signupevent);
            }

            if(title.equals("newgame"))
            {
                logicalagent.newgame();
            }
            if(title.equals("choosecell"))
            {
                bombevent bombevent=g.fromJson(gson,bombevent.class);
                logicalagent.bomb(bombevent.getI(),bombevent.getJ());

            }
            if(title.equals("finish"))
                logicalagent.finish();
            if(title.equals("finish_watching")) {
                finishevent bombevent=g.fromJson(gson,finishevent.class);
                logicalagent.finish_watch(bombevent.getI());
            }

            if(title.equals("start_watch"))
                logicalagent.start_watch();
            if(title.equals("choose_game_watch")) {
                startwatch  startwatch = g.fromJson(gson, startwatch.class);
                logicalagent.choose(startwatch.getI());
            }
            if(title.equals("scoreboard"))
                logicalagent.scoreboard();
            if(title.equals("set_offline"))
                logicalagent.set_offline();
            if(title.equals("show_info"))
                logicalagent.show_info();
            if(title.equals("delete"))
                logicalagent.delete();


        }



    }
}
