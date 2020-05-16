package Controller;

import Model.Account.Account;

public class ProfileManager {
    private Account account;

    public ProfileManager(Account account) {
        this.account = account;
    }

    public String viewPersonalInfo() {
        return account.toString();
    }

    public void editFieldOfProfile(String fieldName, String fieldChange) throws IllegalArgumentException, IllegalAccessException{
        if (fieldName.equals("username")) {
            throw new IllegalAccessException();
        }
        else {
            if (fieldName.equalsIgnoreCase("password")) {
                account.setPassword(fieldChange);
            }
            else if (fieldName.equalsIgnoreCase("name")) {
                account.setName(fieldChange);
            }
            else if (fieldName.equalsIgnoreCase("lastName")) {
                account.setLastName(fieldChange);
            }
            else if (fieldName.equalsIgnoreCase("email")) {
                account.setEmail(fieldChange);
            }
            else if (fieldName.equalsIgnoreCase("phone number")) {
                account.setPhoneNumber(fieldChange);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }
}
