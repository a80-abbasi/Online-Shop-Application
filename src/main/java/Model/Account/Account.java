package Model.Account;

import java.util.ArrayList;

class Field {
    String name;
    String value;
    public Field(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
}

public abstract class Account {
    private static ArrayList<Account> allAccounts = new ArrayList<>();
    protected ArrayList<Field> personalFields = new ArrayList<>();

    public Account(String username, String password, String name, String lastName, String email, String phoneNumber) {
        personalFields.add(new Field("username", username));
        personalFields.add(new Field("password", password));
        personalFields.add(new Field("name", name));
        personalFields.add(new Field("lastName", lastName));
        personalFields.add(new Field("email", email));
        personalFields.add(new Field("phoneNumber", phoneNumber));
        allAccounts.add(this);
    }

    public Account() {
        this("", "", "", "", "", "");
    }

    public String getField(String name) {
        for (Field f : personalFields) {
            if (f.getName().equals(name)) {
                return f.value;
            }
        }
        return null;
    }

    public void setField(String name, String newValue) {
        for (Field personalField : personalFields) {
            if (personalField.name.equals(name)) {
                personalField.value = newValue;
            }
        }
    }

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public static Account getAccountByUsername (String username) {
        for (Account account : allAccounts) {
            for (Field personalField : account.personalFields) {
                if (personalField.name.equals("username")) {
                    return account;
                }
            }
        }
        return null;
    }

    public boolean hasField (String fieldName) {
        for (Field personalField : personalFields) {
            if (personalField.name.equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
