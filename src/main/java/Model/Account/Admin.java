package Model.Account;

import java.util.ArrayList;

public class Admin extends Account{
    private static ArrayList<Admin> allAdmins;

    {
        allAdmins = new ArrayList<>();
    }

    public Admin(String username, String password, String name, String lastName, String email, String phoneNumber) {
        super(username, password, name, lastName, email, phoneNumber);
        allAdmins.add(this);
    }
}
