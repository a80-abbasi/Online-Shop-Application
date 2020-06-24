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

    public void editFieldOfProfile(String fieldName, String fieldChange) throws IllegalArgumentException, IllegalAccessException {
        if (fieldName.equals("username")) {
            throw new IllegalAccessException();
        } else {
            if (fieldName.equalsIgnoreCase("password")) {
                account.setPassword(fieldChange);
            } else if (fieldName.equalsIgnoreCase("name")) {
                account.setName(fieldChange);
            } else if (fieldName.equalsIgnoreCase("lastName")) {
                account.setLastName(fieldChange);
            } else if (fieldName.equalsIgnoreCase("email")) {
                account.setEmail(fieldChange);
            } else if (fieldName.equalsIgnoreCase("phone number")) {
                account.setPhoneNumber(fieldChange);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public String getUsername() {
        return account.getUsername();
    }

    public String getPassword() {
        return account.getPassword();
    }

    public String getFirstName() {
        return account.getName();
    }

    public String getLastName() {
        return account.getPassword();
    }

    public String getEmail() {
        return account.getEmail();
    }

    public String getPhoneNumber() {
        return account.getPhoneNumber();
    }

    public void editPassword(String password) throws IllegalArgumentException{
        if (password.equals("") || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Password.");
        }
        else {
            this.account.setPassword(password);
        }
    }

    public void editFirstName(String firstName) throws IllegalArgumentException {
        if (firstName.matches("[a-zA-Z ]+")){
            this.account.setName(firstName);
        }
        else {
            throw new IllegalArgumentException("Invalid Name.");
        }
    }

    public void editLastName(String lastName) throws IllegalArgumentException {
        if (lastName.matches("[a-zA-Z ]+")){
            this.account.setLastName(lastName);
        }
        else {
            throw new IllegalArgumentException("Invalid Name.");
        }
    }

    public void editEmail(String email) throws IllegalArgumentException {
        if (email.matches(".+?@\\w+\\.\\w+")){
            this.account.setEmail(email);
        }
        else {
            throw new IllegalArgumentException("Invalid Email.");
        }
    }

    public void editPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber.matches("\\d+")){
            this.account.setPhoneNumber(phoneNumber);
        }
        else {
            throw new IllegalArgumentException("Invalid Phone Number.");
        }
    }
}
