package Controller;

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
}
