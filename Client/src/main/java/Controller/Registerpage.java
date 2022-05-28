package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.Globalevent;
import Events.signupevent;
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

public class Registerpage extends Controller{


    @FXML
    private AnchorPane registerpage;

    @FXML
    private TextField newusername;

    @FXML
    private TextField newpassword;

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

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void create(ActionEvent event) {
        System.out.println("hi baby");

        String username=newusername.getText();
        String pass=newpassword.getText();
        if(username!=null&&pass!=null)
        {
            signupevent register=new signupevent();
            register.setPassword(pass);
            register.setUsername(username);
            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("signup");
            Gson gson=new Gson();
            String gsn=gson.toJson(register,register.getClass());
            globalevent.setGson(gsn);
            request.getGlobalevents().add(globalevent);
            System.out.println("2");
        }
        else
        {
            newpassword.setPromptText("error");
            newusername.setText("error");
        }


    }

    public void canregister()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("menu")));
                try {
                    Pane root = loader.load();
                    Scene scene=registerpage.getScene();
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
    public void cantregister()
    {
        newpassword.setPromptText("error");
        newusername.setText("error");
    }
    public void back()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("first_page")));
        try {
            Pane root = loader.load();
            Scene scene=registerpage.getScene();
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
}
