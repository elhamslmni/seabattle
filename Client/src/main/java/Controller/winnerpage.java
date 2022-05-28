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
import javafx.scene.text.Text;
import utils.Request;

import java.io.IOException;

public class winnerpage extends Controller{

    @FXML
    private AnchorPane winnerpage;

    @FXML
    private Text wiinername;
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

    public void initializee(String name)
    {
        wiinername.setText(name);
    }


    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void back() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("menu_page")));
        try {
            Pane root = loader.load();
            Scene scene = winnerpage.getScene();
            scene.setRoot(root);
            menu wellcome = loader.getController();
            request.setCurrentpage(wellcome);
            wellcome.setRequest(request);
            wellcome.setColorconfig(colorconfig);
            wellcome.setPathconfig(pathconfig);
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
