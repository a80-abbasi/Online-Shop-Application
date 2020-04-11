package View;

import Controller.Manager;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    protected ArrayList<Menu> submenus;
    protected Menu parentMenu;
    public static Scanner scanner;
    protected static Manager manager;
    protected static ArrayList<Menu> allMenus = new ArrayList<>();

    public Menu (String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        allMenus.add(this);
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public static void setManager(Manager manager) {
        Menu.manager = manager;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }


    public String getName() {
        return name;
    }

    public void setSubmenus(ArrayList<Menu> submenus) {
        this.submenus = submenus;
    }

    public void show() {
        System.out.println(this.name + ":");
        for (int i = 0; i < submenus.size(); i++) {
            System.out.println(i + ". " + submenus.get(i).getName());
        }

        if (this.parentMenu != null)
            System.out.println((submenus.size() + 1) + ". Back");
        else
            System.out.println((submenus.size() + 1) + ". Exit");
    }

    public void execute() {
        Menu nextMenu = null;
        int chosenMenu = Integer.parseInt(scanner.nextLine());
        if (chosenMenu == submenus.size() + 1) {
            if (this.parentMenu == null)
                System.exit(1);
            else
                nextMenu = this.parentMenu;
        } else
            nextMenu = submenus.get(chosenMenu);
        nextMenu.show();
        nextMenu.execute();
    }
}
