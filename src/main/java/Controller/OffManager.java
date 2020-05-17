package Controller;

import Model.Account.Off;
import Model.Product.Product;

public class OffManager {

    public static boolean isValidInputForOffID (String ID) {
        for (String offID : Off.getAllOffIds()) {
            if (offID.equals(ID)) {
                return true;
            }
        }
        return false;
    }
}
