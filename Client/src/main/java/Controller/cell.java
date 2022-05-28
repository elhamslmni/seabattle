package Controller;
import Events.Globalevent;
import Events.bombevent;
import Events.event;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.Request;

public class cell extends Controller{



    @FXML
    private AnchorPane cell;
    Request request;
    boolean canclick=true;
    int cellnumber;
    public boolean isCanclick() {
        return canclick;
    }

    public void setCanclick(boolean canclick) {
        this.canclick = canclick;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @FXML
    void clicked(MouseEvent event) {

        if(canclick) {
            bombevent bombevent = new bombevent();
            Gson gson = new Gson();
            bombevent.setI(cellnumber / 10);
            bombevent.setJ(cellnumber % 10);
            Globalevent globalevent = new Globalevent();
            globalevent.setTitle("choosecell");
            globalevent.setGson(gson.toJson(bombevent, bombevent.class));
            request.getGlobalevents().add(globalevent);

        }

    }


    public int getCellnumber() {
        return cellnumber;
    }

    public void setCellnumber(int cellnumber) {
        this.cellnumber = cellnumber;
    }
}
