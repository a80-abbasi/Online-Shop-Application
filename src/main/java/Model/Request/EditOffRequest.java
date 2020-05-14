package Model.Request;

import Model.Account.Off;

import java.util.ArrayList;
import java.util.HashMap;

public class EditOffRequest extends Request {
    private static ArrayList<EditOffRequest> allEditOffRequests = new ArrayList<>();
    private Off off;
    private HashMap<String, String> fieldChanges;

    public EditOffRequest(Off off, HashMap<String, String> fieldChanges) {
        super("edit_off_" + allRequests.size(), RequestType.Editing_Off_Request);
        allEditOffRequests.add(this);
        this.off = off;
        this.fieldChanges = fieldChanges;
    }

    public static ArrayList<EditOffRequest> getAllEditOffRequests() {
        return allEditOffRequests;
    }

    public static void setAllEditOffRequests(ArrayList<EditOffRequest> allEditOffRequests) {
        EditOffRequest.allEditOffRequests = allEditOffRequests;
    }

    @Override
    public void acceptRequest() {

    }

    @Override
    public String toString() {
        System.out.println("OffId = " + off.getOffID());
        return null;
        //todo: completing
    }
}
