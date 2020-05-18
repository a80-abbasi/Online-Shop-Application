package Model.Request;

import Model.Account.Off;
import Model.Account.OffStatus;

import java.util.ArrayList;
import java.util.Date;

public class EditOffRequest extends EditAddOffRequest {
    private static ArrayList<EditOffRequest> allEditOffRequests = new ArrayList<>();
    private Off off;

    public EditOffRequest(Off off) {
        super("edit_off_" + allRequests.size(), RequestType.Editing_Off_Request);
        allEditOffRequests.add(this);
        this.setOff(off);
        this.setOffID(off.getOffID());
        this.setStartTime(off.getStartTime());
        this.setEndTime(off.getEndTime());
        this.setOffAmount(off.getOffAmount());
        this.setOffStatus(OffStatus.PENDING_FOR_EDITION);
    }

    public EditOffRequest() {
        this(null);
    }

    public static ArrayList<EditOffRequest> getAllEditOffRequests() {
        return allEditOffRequests;
    }

    public static void setAllEditOffRequests(ArrayList<EditOffRequest> allEditOffRequests) {
        EditOffRequest.allEditOffRequests = allEditOffRequests;
    }

    public void setOff(Off off) {
        this.off = off;
    }

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Off.getOffById(offID) != null && (!(Off.getOffById(offID).equals(off)))) {
            throw new IllegalArgumentException();
        }
        else {
            off.setOffID(offID);
            off.setStartTime(startTime);
            off.setEndTime(endTime);
            off.setOffStatus(OffStatus.CONFIRMED);
            off.setOffAmount(offAmount);
            //off.setOffProducts():??
            //todo: setOffProducts()??
        }
    }

    @Override
    public String toString() {
        return "EditOffRequest{" +
                "off=" + off +
                ", offID='" + offID + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", offAmount=" + offAmount +
                ", offStatus=" + offStatus +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }
}
