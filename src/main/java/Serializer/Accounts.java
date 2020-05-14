package Serializer;

import Model.Account.Account;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Accounts {
    private final static  String pathName = "src\\main\\resources\\accounts.xml";

    @XmlElement(name = "account")
    private ArrayList<Account> allAccounts = new ArrayList<>();
    private Accounts(){
    }

    public static void deserializeXML(){
        File file = new File(pathName);
        if(file.exists() && !file.isDirectory() && file.length() > 0) {
            try(BufferedReader input = Files.newBufferedReader(Paths.get(pathName))) {
                Accounts accounts = JAXB.unmarshal(input, Accounts.class);
                Account.setAllAccounts(accounts.allAccounts);
            } catch (IOException e) {
                System.out.println("Error opening file");
            }
        }
    }

    public static void serializeXML(){
        try(BufferedWriter output = Files.newBufferedWriter(Paths.get(pathName))) {
            Accounts accounts = new Accounts();
            accounts.allAccounts = Account.getAllAccounts();
            JAXB.marshal(accounts, output);
        }
        catch (IOException e) {
            System.out.println("Error opening file");
        }
    }
}
