package Controller;

import Model.Account.Account;

import java.util.ArrayList;

public class EditManager {

    public static ArrayList<Field> getAnAccountPersonalFields(Account account) {
        return account.getPersonalFields();
    }

    public static boolean canEditWithNewString(String field, String newThing) {
        return true;
        //todo:check is newThing true String, dependence its field;
    }

}
