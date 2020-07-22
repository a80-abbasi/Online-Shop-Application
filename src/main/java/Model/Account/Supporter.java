package Model.Account;

import java.util.ArrayList;

public class Supporter extends Account{
    private static ArrayList<Supporter> allSupporters;

    static {
        allSupporters = new ArrayList<>();
    }

    public Supporter(String username, String password, String name, String lastName, String email, String phoneNumber) {
        super(username, password, name, lastName, email, phoneNumber);
        allSupporters.add(this);
    }

    public Supporter() {
        this("", "", "", "", "", "");
    }

    public static void setAllSupporters(ArrayList<Supporter> allSupporters) {
        Supporter.allSupporters = allSupporters;
    }

    public static ArrayList<Supporter> getAllSupports() {
        return allSupporters;
    }


    public static ArrayList<Supporter> getAllSupporters() {
        return allSupporters;
    }

    public static Supporter getSupporterByUserName(String username){
        for (Supporter supporter : allSupporters) {
            if (supporter.getUsername().equalsIgnoreCase(username)){
                return supporter;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Supporter{" +
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
        allSupporters.remove(this);
    }
}

