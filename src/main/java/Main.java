import Controller.LoginAndRegisterManager;
import Controller.ProductsManager;
import Serializer.Accounts;
import View.MainMenu;
import View.Menu;
import View.RegistrationMenus.LoginAndRegisterMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu.setLoginAndRegisterMenu(new LoginAndRegisterMenu(null));
        Menu.setLoginAndRegisterManager(new LoginAndRegisterManager());
        Menu.setProductsManager(new ProductsManager());
        Menu.setScanner(new Scanner(System.in));
        MainMenu mainMenu = new MainMenu();
        mainMenu.execute();
    }

    private static void deserializeXML(){
        Accounts.deserializeXML();
    }

    private static void serializeXML(){
        Accounts.serializeXML();
    }
}
//src\main\resources