package Model.Account.Request;

import Model.Account.Off;

import java.util.HashMap;

public class EditOffRequest extends Request {
    private Off off;
    private HashMap<String, String> fieldChanges;

    public EditOffRequest(Off off, HashMap<String, String> fieldChanges) {
        super("edit_off_" + allRequests.size());
        this.off = off;
        this.fieldChanges = fieldChanges;
    }

    @Override
    public void acceptRequest() {

    }

}
