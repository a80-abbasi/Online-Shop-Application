package Model.Account;

import java.util.ArrayList;

public class Support extends Account{
    private static ArrayList<Support> allSupports;

    static {
        allSupports = new ArrayList<>();
    }

    public Support(String username, String password, String name, String lastName, String email, String phoneNumber) {
        super(username, password, name, lastName, email, phoneNumber);
        allSupports.add(this);
    }

    public Support() {
        this("", "", "", "", "", "");
    }

    public static void setAllAdmins(ArrayList<Admin> allAdmins) {
        Support.allSupports = allSupports;
    }

    public static ArrayList<Support> getAllSupports() {
        return allSupports;
    }


    @Override
    public String toString() {
        return "Support{" +
                "username = '" + username + '\'' +
                ", password = '" + password + '\'' +
                ", name = '" + name + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", email = '" + email + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public void removeUser() {
        allSupports.remove(this);
    }
}
