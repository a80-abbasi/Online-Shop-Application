package Controller;

import Client.Connection;
import Model.Account.Admin;
import Model.Account.Customer;
import Model.Account.Supporter;

public class SupporterProfileManager extends ProfileManager {

    public Supporter supporter;

    public SupporterProfileManager(Supporter supporter) {
        super(supporter);
        this.supporter = supporter;
    }

    public Supporter getSupporter() {
        return supporter;
    }

    public void setLineCondition(boolean lineCondition) {
        String lineConditionStr;
        if (lineCondition) {
            lineConditionStr = "true";
        } else {
            lineConditionStr = "false";
        }
        Connection.sendToServer("set line condition: " + lineConditionStr);
    }
}
