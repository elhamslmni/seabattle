package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.*;
import Model.Logiccell;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import utils.Request;

import java.io.IOException;
import java.util.ArrayList;


public class watchingpage extends Controller{

    @FXML
    private AnchorPane watchpage;

    @FXML
    private GridPane grid1;

    @FXML
    private GridPane grid2;

    @FXML
    private Text plyer1;

    @FXML
    private Text plyer2;

     Request request;
     int i;

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

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    boolean isstarted;
    public void scoreboard(finishgameevent finishgameevent)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("winner_page")));
                try {
                    Pane root = loader.load();
                    Scene scene = watchpage.getScene();
                    scene.setRoot(root);
                    winnerpage wellcome = loader.getController();
                    request.setCurrentpage(wellcome);
                    wellcome.setRequest(request);
                    wellcome.setColorconfig(colorconfig);
                    wellcome.setPathconfig(pathconfig);
                    wellcome.initializee(finishgameevent.getUserame());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



        });
    }

    public void back() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("menu_page")));
        try {
            Pane root = loader.load();
            Scene scene = watchpage.getScene();
            scene.setRoot(root);
            menu wellcome = loader.getController();
            request.setCurrentpage(wellcome);
            wellcome.setRequest(request);
            wellcome.setColorconfig(colorconfig);
            wellcome.setPathconfig(pathconfig);
            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("finish_watching");
            Gson gson=new Gson();
            finishevent finishevent=new finishevent();
            finishevent.setI(i);
            globalevent.setGson(gson.toJson(finishevent,finishevent.getClass()));
            request.getGlobalevents().add(globalevent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void makecell(watchevent watchevent) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ////pink: normal cell
                ////red: destroyed cell with ship
                ////orange:destroyed cell without ship
                ////purple:ship
                i=watchevent.getI();
                ArrayList<ArrayList<Logiccell>> logiccells1=watchevent.getCells1();
                ArrayList<ArrayList<Logiccell>> logiccells2=watchevent.getCells2();
                if(!isstarted)
                {
                    isstarted=true;
                    plyer1.setText(watchevent.getUser1());
                    plyer2.setText(watchevent.getUser2());
                }


                int column=0;
                int row=0;

                /////cell 1
                System.out.println("in-makecell");
                for (int i = 0; i < 110; i++) {
                    System.out.println("in-for");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("cell")));
                    try {
                        AnchorPane anchorPane = loader.load();
                        cell cel=loader.getController();
                        cel.setCellnumber(i-10);
                        cel.setCanclick(false);


                        if (column == 10) {
                            column = 0;
                            row++;
                        }
                        if (i < 10) {
                            anchorPane.setStyle(colorconfig.getProperty("color5"));
                        } else {
                            int n = i - 10;
                            int x = n / 10;
                            int y = n % 10;
                            if (logiccells1.get(x).get(y).isIsactive())
                                anchorPane.setStyle(colorconfig.getProperty("color3"));
                            else {
                                if (logiccells1.get(x).get(y).getLogicship() != null)
                                    anchorPane.setStyle(colorconfig.getProperty("color1"));
                                else anchorPane.setStyle(colorconfig.getProperty("color2"));
                            }


                        }
                        grid1.add(anchorPane, column++,row); //(child,column,row)
                        //set grid width
                        grid1.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid1.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid1.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid1.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid1.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid1.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                column=0;
                row=0;

                for (int i = 0; i < 110; i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("cell")));
                    try {
                        AnchorPane anchorPane = loader.load();
                        cell cel = loader.getController();
                        cel.setCellnumber(i - 10);
                        cel.setCanclick(false);
                        if (column == 10) {
                            column = 0;
                            row++;
                        }
                        if (i < 10) {
                            anchorPane.setStyle(colorconfig.getProperty("color5"));
                        } else {
                            int n = i - 10;
                            int x = n / 10;
                            int y = n % 10;
                            if (logiccells2.get(x).get(y).isIsactive())
                                anchorPane.setStyle(colorconfig.getProperty("color3"));
                            else {
                                if (logiccells2.get(x).get(y).getLogicship() != null)
                                    anchorPane.setStyle(colorconfig.getProperty("color1"));
                                else anchorPane.setStyle(colorconfig.getProperty("color2"));
                            }


                        }
                        grid2.add(anchorPane, column++, row); //(child,column,row)
                        //set grid width
                        grid2.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid2.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid2.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid2.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid2.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid2.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }







            }


        });


    }




    public void exit()
    {
        Globalevent globalevent1=new Globalevent();
        globalevent1.setTitle("set_offline");
        Globalevent globalevent=new Globalevent();
        globalevent.setTitle("finish_watching");
        Gson gson=new Gson();
        finishevent finishevent=new finishevent();
        finishevent.setI(i);
        globalevent.setGson(gson.toJson(finishevent,finishevent.getClass()));
        request.getGlobalevents().add(globalevent);
        System.exit(0);
    }
}