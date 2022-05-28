package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.gameevent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Request;

import java.io.IOException;

public class Waitingpage extends Controller{

    Request request;
    @FXML
    private AnchorPane waitingpage;


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



    public void start(gameevent gameevent)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("game_page")));
                try {
                    Pane root = loader.load();
                    Scene scene=waitingpage.getScene();
                    scene.setRoot(root);
                    gameboard gameboard=loader.getController();
                    request.setCurrentpage(gameboard);
                    gameboard.setRequest(request);
                    gameboard.setColorconfig(colorconfig);
                    gameboard.setPathconfig(pathconfig);
                    gameboard.makeboard(gameevent);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });





    }

}
