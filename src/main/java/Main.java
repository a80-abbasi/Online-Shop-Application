import Controller.LoginAndRegisterManager;
import Controller.ProductsManager;
import Serializer.Accounts;
import Serializer.Categories;
import Serializer.Products;
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
        Products.deserializeXML();
        Categories.deserializeXML();
    }

    private static void serializeXML(){
        Accounts.serializeXML();
        Products.serializeXML();
        Categories.serializeXML();
    }
}
//src\main\resources