package utils;

import Controller.loginpage;
import Events.Globalevent;
import Events.event;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.util.Scanner;

public class inputlistener extends  Thread{

    Scanner scanner;
    Request request;
    PrintWriter printWriter;
    public inputlistener(Scanner scanner,PrintWriter printWriter,Request request)
    {
        this.scanner=scanner;
        this.printWriter=printWriter;
        this.request=request;
    }


    public void run()
    {
        Gson gson=new Gson();
        Globalevent globalevent;
        String send="",x="";
        while (true)
        {

            if(request.getGlobalevents().size()>0) {
                globalevent = request.getGlobalevents().remove(0);

            }
            else
            {
                globalevent=new Globalevent();
                globalevent.setTitle("nothing");
                globalevent.setGson("...");
            }
            send=gson.toJson(globalevent,globalevent.getClass());
            printWriter.println(send);
            printWriter.flush();
            x=scanner.nextLine();
            globalevent=gson.fromJson(x,Globalevent.class);
            checkservermessage.check(globalevent,request);
        }
    }

}
