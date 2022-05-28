package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.Globalevent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Request;

import java.io.IOException;


public class menu extends Controller{
    Request request;

    @FXML
    private AnchorPane menupage;
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    Pathconfig pathconfig;
    Colorconfig colorconfig;

    public Pathconfig getPathconfig() {
        return pathconfig;
    }

    public void setPathconfig(Pathconfig pathconfig) {
        this.pathconfig = pathconfig;
    }

    public Colorconfig getColorconfig() {
        return colorconfig;
    }

    public void setColorconfig(Colorconfig colorconfig) {
        this.colorconfig = colorconfig;
    }


    @FXML
    void newgame(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("waiting_page")));
        try {
            Pane root = loader.load();
            Scene scene=menupage.getScene();
            scene.setRoot(root);
            Waitingpage waitingpage=loader.getController();
            request.setCurrentpage(waitingpage);
            waitingpage.setRequest(request);
            waitingpage.setColorconfig(colorconfig);
            waitingpage.setPathconfig(pathconfig);
            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("newgame");
            globalevent.setGson("...");
            request.getGlobalevents().add(globalevent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void watchgame()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("choose_game")));
        try {
            Pane root = loader.load();
            Scene scene=menupage.getScene();
            scene.setRoot(root);
            choosegame watchingpage=loader.getController();
            request.setCurrentpage(watchingpage);
            watchingpage.setRequest(request);
            watchingpage.setColorconfig(colorconfig);
            watchingpage.setPathconfig(pathconfig);
            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("start_watch");
            globalevent.setGson("...");
            request.getGlobalevents().add(globalevent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void scoreboard()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("scoreboard_page")));
        try {
            Pane root = loader.load();
            Scene scene=menupage.getScene();
            scene.setRoot(root);
            scoreboard scoreboard=loader.getController();
            request.setCurrentpage(scoreboard);
            scoreboard.setRequest(request);
           scoreboard.setColorconfig(colorconfig);
           scoreboard.setPathconfig(pathconfig);
          Globalevent globalevent=new Globalevent();
          globalevent.setTitle("scoreboard");
          globalevent.setGson("...");
          request.getGlobalevents().add(globalevent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void back()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("first_page")));
        try {
            Pane root = loader.load();
            Scene scene=menupage.getScene();
            scene.setRoot(root);
            firstpage firstpage=loader.getController();
            request.setCurrentpage(firstpage);
            firstpage.setRequest(request);
            firstpage.setColorconfig(colorconfig);
            firstpage.setPathconfig(pathconfig);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void show_info()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("info_page")));
        try {
            Pane root = loader.load();
            Scene scene=menupage.getScene();
            scene.setRoot(root);
            infopage infopage=loader.getController();
            request.setCurrentpage(infopage);
            infopage.setRequest(request);
            infopage.setColorconfig(colorconfig);
           infopage.setPathconfig(pathconfig);
            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("show_info");
            globalevent.setGson("...");
            request.getGlobalevents().add(globalevent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exit()
    {
        Globalevent globalevent=new Globalevent();
        globalevent.setTitle("set_offline");
           System.exit(0);
    }

    public void delete_acc()
    {
        Globalevent globalevent=new Globalevent();
        globalevent.setGson("...");
        globalevent.setTitle("delete");
        request.getGlobalevents().add(globalevent);
        exit();
    }



}
