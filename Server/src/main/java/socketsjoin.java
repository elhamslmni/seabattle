import Database.Modelloader;
import Database.Modelsaver;
import Logicalagent.logicalagent;
import Logicalagent.GameManager;
import utils.Request;
import utils.inputlistener;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class socketsjoin extends Thread{

    Modelsaver modelsaver;
    Modelloader modelloader;
    GameManager gameManager;
    socketsjoin(Modelsaver modelsaver, Modelloader modelloader)
    {
        this.modelloader=modelloader;
        this.modelsaver=modelsaver;
        this.modelloader.loadallGames();
        this.modelloader.loadallusers();
        gameManager=new GameManager();
    }

    public void run()
    {
        ServerSocket server = null;
        try {
            server = new ServerSocket(5400);
            Socket sock = null;
            while (true) {
                sock=server.accept();
                PrintWriter printWriter = new PrintWriter(sock.getOutputStream());
                Scanner scanner = new Scanner(sock.getInputStream());
                logicalagent logicalagent=new logicalagent();
                Request request=new Request();
                logicalagent.setModelloader(modelloader);
                logicalagent.setModelsaver(modelsaver);
                logicalagent.setGameManager(gameManager);
                logicalagent.setRequest(request);
                inputlistener inputlistener = new inputlistener(scanner, printWriter,logicalagent);
                inputlistener.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
