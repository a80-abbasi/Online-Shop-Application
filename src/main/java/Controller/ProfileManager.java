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

    public void editFieldOfProfile(String fieldName, String fieldChange) {
        if (fieldName.equals("username")) {
            throw new IllegalArgumentException();
        }
        else {
            if (fieldName.equals("password")) {
                account.setPassword(fieldChange);
            }
            else if (fieldName.equals("name")) {
                account.setName(fieldChange);
            }
            else if (fieldName.equals("lastName")) {
                account.setLastName(fieldChange);
            }
            else if (fieldName.equals("email")) {
                account.setEmail(fieldChange);
            }
            else if (fieldName.equals("phone number")) {
                account.setPhoneNumber(fieldChange);
            }
        }
    }
}
