package Model.Request;

import Model.Account.Off;

import java.util.HashMap;

public class EditOffRequest extends Request {
    private Off off;
    private HashMap<String, String> fieldChanges;

    public EditOffRequest(Off off, HashMap<String, String> fieldChanges) {
        super("edit_off_" + allRequests.size(), RequestType.Editing_Off_Request);
        this.off = off;
        this.fieldChanges = fieldChanges;
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
