package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.Globalevent;
import Events.loginevent;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Request;

import java.io.IOException;

public class loginpage extends Controller{


    @FXML
    private TextField usernamefield;

    @FXML
    private TextField passwordfield;
    @FXML
    private AnchorPane loginpage;

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
    public void back()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("first_page")));
        try {
            Pane root = loader.load();
            Scene scene=loginpage.getScene();
            scene.setRoot(root);
            firstpage wellcome=loader.getController();
            request.setCurrentpage(wellcome);
            wellcome.setRequest(request);
            wellcome.setColorconfig(colorconfig);
            wellcome.setPathconfig(pathconfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String username=usernamefield.getText();
                String pass=passwordfield.getText();
                if(username!=null&&pass!=null)
                {
                    loginevent logineventt=new loginevent();
                    logineventt.setPassword(pass);
                    logineventt.setUsername(username);
                    Globalevent globalevent=new Globalevent();
                    globalevent.setTitle("login");
                    Gson gson=new Gson();
                    String gsn=gson.toJson(logineventt,logineventt.getClass());
                    globalevent.setGson(gsn);
                    request.getGlobalevents().add(globalevent);
                }
                else
                {
                    usernamefield.setPromptText("error");
                    passwordfield.setText("error");
                }


            }
        });

    }
    public void canlogin()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("menu_page")));
                try {
                    Pane root = loader.load();
                    Scene scene=loginpage.getScene();
                    scene.setRoot(root);
                    menu wellcome=loader.getController();
                    request.setCurrentpage(wellcome);
                    wellcome.setRequest(request);
                    wellcome.setColorconfig(colorconfig);
                    wellcome.setPathconfig(pathconfig);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
    }
    public void cantlogin()
    {
        usernamefield.setPromptText("error");
        passwordfield.setText("error");

    }
    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }





}
