package Model.Account;

import java.util.ArrayList;



public abstract class Account {
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    private ArrayList<Field> personalFields = new ArrayList<>();

    public Account(String username, String password, String name, String lastName, String email, String phoneNumber) {
        personalFields.add(new Field("username", username));
        personalFields.add(new Field("password", password));
        personalFields.add(new Field("name", name));
        personalFields.add(new Field("lastName", lastName));
        personalFields.add(new Field("email", email));
        personalFields.add(new Field("phoneNumber", phoneNumber));
        allAccounts.add(this);
    }

    public String getFieldValue(String name) {
        for (Field personalField : personalFields) {
            if (personalField.getName().equals(name)) {
                return personalField.getValue();
            }
        }
        return null;
    }

    public void setFieldValue(String name, String newValue) {
        for (Field personalField : personalFields) {
            if (personalField.getName().equals(name)) {
                personalField.setValue(newValue);
            }
        }
    }

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static Account getAccountByUsername (String username) {
        for (Account account : allAccounts) {
            for (Field personalField : account.personalFields) {
                if (personalField.getName().equals("username")) {
                    return account;
                }
            }
        }
        return null;
    }

    public boolean hasField (String fieldName) {
        for (Field personalField : personalFields) {
            if (personalField.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Field> getPersonalFields() {
        return personalFields;
    }
}
