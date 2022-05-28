package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.Globalevent;
import Events.choosegameevent;
import Events.startwatch;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Request;

import java.io.IOException;
import java.util.ArrayList;

public class choosegame extends  Controller{

    @FXML
    private AnchorPane choosepage4;

    @FXML
    private ChoiceBox<String> choosebox;

    Request request;
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

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void initializee(choosegameevent choosegameevent)
    {
        System.out.println("hihi");
        if(choosegameevent.getGames()!=null) {
            System.out.println("ini "+choosegameevent.getGames().size());
            choosebox.getItems().addAll(choosegameevent.getGames());
            choosebox.getItems().add("...");

            choosebox.setOnAction(this::choose);
        }
    }

    private void choose(ActionEvent actionEvent) {


         String game=choosebox.getValue();
         int i=game.charAt(0);
        Globalevent globalevent=new Globalevent();
        globalevent.setTitle("choose_game_watch");
        startwatch startwatch=new startwatch();
        startwatch.setI(i);
        Gson gson=new Gson();
        globalevent.setGson(gson.toJson(startwatch,startwatch.getClass()));
        request.getGlobalevents().add(globalevent);
        watch();






    }


    public void watch()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("watch_page")));
        try {
            Pane root = loader.load();
            Scene scene=choosepage4.getScene();
            scene.setRoot(root);
            watchingpage watchingpage=loader.getController();
            request.setCurrentpage(watchingpage);
            watchingpage.setRequest(request);
            watchingpage.setColorconfig(colorconfig);
            watchingpage.setPathconfig(pathconfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void exit(ActionEvent event) {
        Globalevent globalevent=new Globalevent();
        globalevent.setTitle("set_offline");
        System.exit(0);
    }
    public void back() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("menu_page")));
        try {
            Pane root = loader.load();
            Scene scene = choosepage4.getScene();
            scene.setRoot(root);
            menu menu = loader.getController();
            request.setCurrentpage(menu);
            menu.setRequest(request);
            menu.setColorconfig(colorconfig);
            menu.setPathconfig(pathconfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
