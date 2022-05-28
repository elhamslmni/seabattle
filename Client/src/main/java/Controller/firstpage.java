package Controller;


import Config.Colorconfig;
import Config.Pathconfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Request;

import java.io.IOException;

public class firstpage extends Controller{

    Request request;

    @FXML
    private AnchorPane firstpage;

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
    public void login()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphics/loginpage.fxml"));
        try {
            Pane root = loader.load();
            Scene scene=firstpage.getScene();
            scene.setRoot(root);
            loginpage wellcome=loader.getController();
            request.setCurrentpage(wellcome);
            wellcome.setRequest(request);
            wellcome.setColorconfig(colorconfig);
            wellcome.setPathconfig(pathconfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void register()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/graphics/Registerpage.fxml"));
        try {
            Pane root = loader.load();
            Scene scene=firstpage.getScene();
            scene.setRoot(root);
            Registerpage wellcome=loader.getController();
            request.setCurrentpage(wellcome);
            wellcome.setRequest(request);
            wellcome.setColorconfig(colorconfig);
            wellcome.setPathconfig(pathconfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exit()
    {
         System.exit(0);
    }

}
