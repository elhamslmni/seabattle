package Logicalagent;

import Database.Modelsaver;
import Events.Globalevent;
import Events.choosegameevent;
import Events.finishgameevent;
import Events.gameevent;
import Logicalagent.PlayGame;
import Logicalagent.logicalagent;
import Model.Game;
import Model.Logiccell;
import Model.Logicship;
import Model.User;
import com.google.gson.Gson;
import utils.Request;
import utils.utilobject;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    ArrayList<logicalagent> logicalagents;
    ArrayList<Game> games;
    ArrayList<PlayGame> playGames;


    public GameManager()
    {
        logicalagents=new ArrayList<>();
        playGames=new ArrayList<>();
        games=new ArrayList<>();
    }


    public void newgame(logicalagent logicalagent)
    {
        logicalagents.add(logicalagent);
        if(logicalagents.size()>1)
            startgame();

    }

    public void choose_game_watch(Request request)
    {
          ArrayList<String> gamess=new ArrayList<>();
          String x="";
          for(int g=0;g<games.size();g++)
          {
              x=String.valueOf(games.get(g).getI())+" :"+games.get(g).getUsers().get(0).getUsername()+"   ships:"+games.get(g).getShips1() +"  &"+games.get(g).getUsers().get(1).getUsername()+"   ships:"+games.get(g).getShips2();
              gamess.add(x);
          }
          Globalevent globalevent=new Globalevent();
          globalevent.setTitle("choose_game");
          Gson gson=new Gson();
        choosegameevent choosegameevent=new choosegameevent();
        choosegameevent.setGames(gamess);
          globalevent.setGson(gson.toJson(choosegameevent,choosegameevent.getClass()));
          request.getGlobalevents().add(globalevent);
    }


    public void startwatch(Request request, int x)
    {

        x-=48;
        for(int g=0;g<playGames.size();g++)
        {

            if(playGames.get(g).getI()==x)
            {

                playGames.get(g).getWatchers().add(request);
            }
        }
    }
   public void finish_watch(int i, Request request)
   {
       for(int g=0;g<playGames.size();g++)
           if(playGames.get(g).getI()==i)
               playGames.get(g).getWatchers().remove(request);
   }
    public void startgame()
    {

        int i=games.size()+1;
        ArrayList<ArrayList<Logiccell> >logiccells1=makecell();
        ArrayList<ArrayList<Logiccell> >logiccells2=makecell();
        System.out.println(2);

        Gson gson=new Gson();
        Game game=new Game(logicalagents.get(0).getUser(),logicalagents.get(1).getUser());
        game.setCells1(logiccells1);
        game.setCells2(logiccells2);
        game.setI(i);
        games.add(game);




        logicalagents.get(0).setGame(game);
        logicalagents.get(1).setGame(game);



        gameevent gameevent=new gameevent();
        gameevent gameevent1=new gameevent();
        gameevent.setCells2(logiccells2);
        gameevent.setCells1(logiccells1);
        gameevent1.setCells2(logiccells2);
        gameevent1.setCells1(logiccells1);

        gameevent.setTurn(1);
        gameevent.setUsernumber(1);
        gameevent.setUsername(logicalagents.get(1).getUser().getUsername());
        Globalevent globalevent=new Globalevent();
        Globalevent globalevent1=new Globalevent();
        globalevent.setGson(gson.toJson(gameevent,gameevent.class));
        globalevent.setTitle("startgame");
        logicalagents.get(0).getRequest().getGlobalevents().add(globalevent);

        gameevent1.setTurn(1);
        gameevent1.setUsernumber(2);
        gameevent1.setUsername(logicalagents.get(0).getUser().getUsername());
        globalevent1.setGson(gson.toJson(gameevent1,gameevent.class));
        globalevent1.setTitle("startgame");
        logicalagents.get(1).getRequest().getGlobalevents().add(globalevent1);

        PlayGame playGame=new PlayGame(logicalagents.get(0).getRequest(),logicalagents.get(1).getRequest(),game);
        playGame.setI(i);
        playGames.add(playGame);
        logicalagents.get(0).setPlayGame(playGame);
        logicalagents.get(1).setPlayGame(playGame);
        playGame.start();
        logicalagents.get(0).getUser().setGamesnumber(logicalagents.get(0).getUser().getGamesnumber()+1);
        logicalagents.get(1).getUser().setGamesnumber(logicalagents.get(0).getUser().getGamesnumber()+1);
        Modelsaver.saveuser( logicalagents.get(0).getUser());
        Modelsaver.saveuser( logicalagents.get(1).getUser());
        ////0 0 >?
         logicalagents.remove(0);
        logicalagents.remove(0);
        ////make boards


    }
    public void finishgame(Game game, User winner)
    {
        Gson gson=new Gson();
        games.remove(game);
        System.out.println(game.getI()+"   gamee");
        winner.setWins(winner.getWins()+1);
        PlayGame playGame = null;
        for(int g=0;g<playGames.size();g++) {
            System.out.println(playGames.get(g).getI()+" pgamee");
            if (playGames.get(g).getI() == game.getI())
                playGame = playGames.get(g);
        }
            playGames.remove(playGame);
        playGame.setExit(0);

        /////handle sync
        finishgameevent finishgameevent=new finishgameevent();
        finishgameevent.setUserame(winner.getUsername());



        Globalevent globalevent=new Globalevent();
        globalevent.setTitle("finish-game-watch");
        globalevent.setGson(gson.toJson(finishgameevent,finishgameevent.class));
        for(int g=0;g<playGame.getWatchers().size();g++)
            playGame.getWatchers().get(g).getGlobalevents().add(globalevent);



        globalevent.setTitle("finish-gameplay");
        playGame.getRequest1().getGlobalevents().add(globalevent);
        playGame.getRequest2().getGlobalevents().add(globalevent);


        Modelsaver.saveuser(winner);


    }



    public ArrayList<ArrayList<Logiccell>> makecell()
    {

        ArrayList<ArrayList<Logiccell> >logiccells=new ArrayList<>();
        for(int g=0;g<10;g++)
        {
            logiccells.add(new ArrayList<>());
        }
        for(int g=0;g<10;g++)
        {
            for(int i=0;i<10;i++)
            {
                Logiccell logiccell=new Logiccell();
                logiccell.setIsactive(true);
                logiccell.setLogicship(null);
                logiccell.setI(g);
                logiccell.setJ(i);
                logiccells.get(g).add(logiccell);
            }
        }

        Logicship battleship=new Logicship();
        battleship.setSize(4);
        utilobject utilobject=new utilobject();
        utilobject.setX(1);
        utilobject.setY(1);
        battleship.addutil(utilobject);

        utilobject utilobject1=new utilobject();
        utilobject1.setX(1);
        utilobject1.setY(2);
        battleship.addutil(utilobject1);


        utilobject utilobject2=new utilobject();
        utilobject2.setX(1);
        utilobject2.setY(3);
        battleship.addutil(utilobject2);


        utilobject utilobject3=new utilobject();
        utilobject3.setX(1);
        utilobject3.setY(4);
        battleship.addutil(utilobject3);

        logiccells.get(1).get(1).setLogicship(battleship);
        logiccells.get(1).get(2).setLogicship(battleship);
        logiccells.get(1).get(3).setLogicship(battleship);
        logiccells.get(1).get(4).setLogicship(battleship);
        System.out.println(battleship.getUtilobjects().size()+" lllll");

        Logicship cruiser1=new Logicship();
        cruiser1.setSize(3);

        utilobject utilobject4=new utilobject();
        utilobject4.setX(1);
        utilobject4.setY(6);
        cruiser1.addutil(utilobject4);

        utilobject utilobject5=new utilobject();
        utilobject5.setX(2);
        utilobject5.setY(6);
        cruiser1.addutil(utilobject5);


        utilobject utilobject6=new utilobject();
        utilobject6.setX(3);
        utilobject6.setY(6);
        cruiser1.addutil(utilobject6);

        logiccells.get(1).get(6).setLogicship(cruiser1);
        logiccells.get(2).get(6).setLogicship(cruiser1);
        logiccells.get(3).get(6).setLogicship(cruiser1);



        Logicship cruiser2=new Logicship();
        cruiser2.setSize(3);

        utilobject utilobject7=new utilobject();
        utilobject7.setX(6);
        utilobject7.setY(6);
        cruiser2.addutil(utilobject7);

        utilobject utilobject8=new utilobject();
        utilobject8.setX(6);
        utilobject8.setY(7);
        cruiser2.addutil(utilobject8);

        utilobject utilobject9=new utilobject();
        utilobject9.setX(6);
        utilobject9.setY(8);
        cruiser2.addutil(utilobject9);
        logiccells.get(6).get(6).setLogicship(cruiser2);
        logiccells.get(6).get(7).setLogicship(cruiser2);
        logiccells.get(6).get(8).setLogicship(cruiser2);


        Logicship destroyer1=new Logicship();
        destroyer1.setSize(2);

        utilobject utilobjecta=new utilobject();
        utilobjecta.setX(2);
        utilobjecta.setY(9);
        destroyer1.addutil(utilobjecta);

        utilobject utilobjectb=new utilobject();
        utilobjectb.setX(3);
        utilobjectb.setY(9);
        destroyer1.addutil(utilobjectb);
        logiccells.get(2).get(9).setLogicship(destroyer1);
        logiccells.get(3).get(9).setLogicship(destroyer1);


        Logicship destroyer2=new Logicship();
        destroyer2.setSize(2);
        utilobject utilobjectc=new utilobject();
        utilobjectc.setX(7);
        utilobjectc.setY(4);
        destroyer2.addutil(utilobjectc);

        utilobject utilobjectd=new utilobject();
        utilobjectd.setX(8);
        utilobjectd.setY(4);
        destroyer2.addutil(utilobjectd);
        logiccells.get(7).get(4).setLogicship(destroyer2);
        logiccells.get(8).get(4).setLogicship(destroyer2);


        Logicship destroyer3=new Logicship();
        destroyer3.setSize(2);

        utilobject utilobjectf=new utilobject();
        utilobjectf.setX(5);
        utilobjectf.setY(1);
        destroyer3.addutil(utilobjectf);

        utilobject utilobjectg=new utilobject();
        utilobjectg.setX(5);
        utilobjectg.setY(2);
        destroyer3.addutil(utilobjectg);
        logiccells.get(5).get(1).setLogicship(destroyer3);
        logiccells.get(5).get(2).setLogicship(destroyer3);



        Logicship frigate1=new Logicship();
        frigate1.setSize(1);
        utilobject utilobjectt=new utilobject();
        utilobjectt.setX(3);
        Random random=new Random();
        utilobjectt.setY(random.nextInt(5));
        frigate1.addutil(utilobjectt);
        logiccells.get(3).get(utilobjectt.getY()).setLogicship(frigate1);

        Logicship frigate2=new Logicship();
        utilobject utilobjecto=new utilobject();
        frigate2.setSize(1);
        utilobjecto.setX(0);
        utilobjecto.setY(9);
        frigate2.addutil(utilobjecto);
        logiccells.get(0).get(9).setLogicship(frigate2);

        Logicship frigate3=new Logicship();
        utilobject utilobjectk=new utilobject();
        frigate3.setSize(1);
        utilobjectk.setX(9);
        utilobjectk.setY(random.nextInt(3));
        frigate3.addutil(utilobjectk);
        logiccells.get(9).get(utilobjectk.getY()).setLogicship(frigate3);


        Logicship frigate4=new Logicship();
        utilobject utilobjectn=new utilobject();
        frigate4.setSize(1);
        utilobjectn.setX(8);
        utilobjectn.setY(7);
        frigate4.addutil(utilobjectn);
        logiccells.get(8).get(7).setLogicship(frigate4);


        return logiccells;
    }









}
