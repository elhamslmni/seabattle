import Config.Colorconfig;
import Config.Pathconfig;
import Controller.firstpage;
import Controller.gameboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Request;
import utils.inputlistener;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class main extends Application {
    public static void main (String[] args) throws IOException, InterruptedException {
        launch(args);

    /*

        thread thread=new thread(scanner);
        thread.start();
        thread2 thread2=new thread2(send);
        thread2.start();
        thread.join();
        thread2.join();   */


    }


    @Override
    public void start(Stage primaryStage) throws Exception {



        Pathconfig pathconfig=new Pathconfig();
        Colorconfig colorconfig=new Colorconfig();

        File file1=new File("Config/path");
        File file2=new File("Config/color");

        FileReader fileReader1=new FileReader(file1);
        FileReader fileReader2=new FileReader(file2);

        pathconfig.load(fileReader1);
        colorconfig.load(fileReader2);




        FXMLLoader loader=new FXMLLoader(getClass().getResource(pathconfig.getProperty("first_page")));
        AnchorPane root=loader.load();
        Request request=new Request();
        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(root, 1500, 800));
//        primaryStage.initStyle(StageStyle.UNDECORATED);

        firstpage first=loader.getController();
        Socket sock = new Socket("127.0.0.1", 5400);
        PrintWriter send=new PrintWriter(sock.getOutputStream());
        first.setRequest(request);
        first.setColorconfig(colorconfig);
        first.setPathconfig(pathconfig);
        primaryStage.show();
        Scanner scanner = new Scanner(sock.getInputStream());
        inputlistener inputlistener=new inputlistener(scanner,send,request);
        inputlistener.start();

    }
}

