package Controller;

import Config.Colorconfig;
import Config.Pathconfig;
import Events.Globalevent;
import Events.finishgameevent;
import Events.gameevent;
import Model.Logiccell;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import utils.Request;

public class gameboard extends Controller {

    @FXML
    private GridPane grid;
    @FXML
    private GridPane grid1;
    @FXML
    private AnchorPane gameboard;
   public Text player2;
    Request request;
    int number;
    int turn;
    String nextplayer;
    boolean isstarted;
    @FXML
    private Text timer;
    @FXML
    private Text turnn;

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

    public void back() {

            Globalevent globalevent=new Globalevent();
            globalevent.setTitle("finish");
            globalevent.setGson("..");
            request.getGlobalevents().add(globalevent);

    }

    public void makeboard(gameevent gameevent) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                turn=gameevent.getTurn();
                timer.setText(String.valueOf(gameevent.getTime()/1000));
                ////pink: normal cell
                ////red: destroyed cell with ship
                ////orange:destroyed cell without ship
                ////purple:ship
                ArrayList<ArrayList<Logiccell>> logiccells1=new ArrayList<>();
                ArrayList<ArrayList<Logiccell>> logiccells2=new ArrayList<>();
                if(!isstarted)
                {
                    isstarted=true;
                    number=gameevent.getUsernumber();
                    nextplayer= gameevent.getUsername();
                }
                if(number==1)
                {
                    logiccells1=gameevent.getCells1();
                    logiccells2=gameevent.getCells2();

                }
                if(number==2)
                {
                    logiccells2=gameevent.getCells1();
                    logiccells1=gameevent.getCells2();

                }
               player2.setText(nextplayer);
                if(turn==number)
                {
                   turnn.setText("your turn");
                }
                else turnn.setText("wait");

                int column=0;
                int row=0;

                /////cell 1
                for (int i = 0; i < 110; i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("cell")));
                    try {
                        AnchorPane anchorPane = loader.load();
                        cell cel=loader.getController();
                        cel.setCellnumber(i-10);
                        cel.setRequest(request);
                        cel.setCanclick(false);


                        if (column == 10) {
                            column = 0;
                            row++;
                        }
                        if(i<10){
                            anchorPane.setStyle(colorconfig.getProperty("color5"));
                        }
                        else {
                            int n=i-10;
                            int x=n/10;
                            int y=n%10;
                            if(logiccells1.get(x).get(y).isIsactive()&&logiccells1.get(x).get(y).getLogicship()!=null)
                                anchorPane.setStyle(colorconfig.getProperty("color3"));
                            else
                            {
                                if(logiccells1.get(x).get(y).isIsactive())
                                    anchorPane.setStyle(colorconfig.getProperty("color6"));
                                else {
                                    if(logiccells1.get(x).get(y).getLogicship()!=null)
                                        anchorPane.setStyle(colorconfig.getProperty("color1"));
                                    else    anchorPane.setStyle(colorconfig.getProperty("color2"));
                                }
                            }

                        }
                        grid.add(anchorPane, column++,row); //(child,column,row)
                        //set grid width
                        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        grid.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        grid.setMaxHeight(Region.USE_PREF_SIZE);

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
                         cel.setRequest(request);
                         if(turn!=number)
                             cel.canclick=false;
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
                                anchorPane.setStyle(colorconfig.getProperty("color4"));
                            else {
                                cel.setCanclick(false);
                                if (logiccells2.get(x).get(y).getLogicship() != null)
                                    anchorPane.setStyle(colorconfig.getProperty("color1"));
                                else anchorPane.setStyle(colorconfig.getProperty("color2"));
                            }


                        }
                        grid1.add(anchorPane, column++, row); //(child,column,row)
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







            }


        });


    }

    public void scoreboard(finishgameevent finishgameevent)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader loader = new FXMLLoader(getClass().getResource(pathconfig.getProperty("winner_page")));
                try {
                    Pane root = loader.load();
                    Scene scene = gameboard.getScene();
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
}