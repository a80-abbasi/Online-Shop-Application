package Model.Account;

import java.util.ArrayList;

public class Admin extends Account{
    private static ArrayList<Admin> allAdmins;

    static {
        allAdmins = new ArrayList<>();
    }

    public Admin(String username, String password, String name, String lastName, String email, String phoneNumber) {
        super(username, password, name, lastName, email, phoneNumber);
        allAdmins.add(this);
    }

    public Admin() {
        this("", "", "", "", "", "");
    }

    public static void setAllAdmins(ArrayList<Admin> allAdmins) {
        Admin.allAdmins = allAdmins;
    }

    public static ArrayList<Admin> getAllAdmins() {
        return allAdmins;
    }

    public static Admin getAdminByUserName(String username){
        for (Admin admin : allAdmins) {
            if (admin.getUsername().equalsIgnoreCase(username)){
                return admin;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Admin{" +
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
        allAdmins.remove(this);
    }
}
