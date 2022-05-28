package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.Globalevent;
import Events.finishevent;
import Events.infoevent;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Request;

import java.io.IOException;

public class infopage extends Controller{

    @FXML
    private AnchorPane infopage;

    @FXML
    private Label infolabel;
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

    public  void show_info(infoevent infoevent)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String x="";
                x+="username: ";
                x+=infoevent.getUsername();
                x+="\n";
                x+="\n";
                x+="\n";
                x+="\n";
                x+="games number: ";
                x+=String.valueOf(infoevent.getGames());
                x+="\n";
                x+="\n";
                x+="\n";
                x+="\n";
                x+="wins number: ";
                x+=String.valueOf(infoevent.getWins());
                infolabel.setText(x);
            }





    });
    }

    public void back() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("menu_page")));
        try {
            Pane root = loader.load();
            Scene scene = infopage.getScene();
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
    @FXML
    void exit(ActionEvent event) {
        Globalevent globalevent=new Globalevent();
        globalevent.setTitle("set_offline");
        System.exit(0);
    }
}

