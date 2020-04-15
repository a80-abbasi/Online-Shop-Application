package View;

import java.util.ArrayList;

public class LoginAndRegisterMenu extends Menu {
    public LoginAndRegisterMenu(Menu parentMenu) {
        super("Login Menu", parentMenu);
        ArrayList<Menu> subMenus = new ArrayList<>();
        subMenus.add(new RegistrationMenu(this));
        subMenus.add(getLoginMenu());
        this.setSubmenus(subMenus);
    }

    private Menu getLoginMenu(){
        return new Menu("Login", this) {
            @Override
            public void execute() {
                System.out.println("Enter your username:");
                String userName = scanner.nextLine().trim();
                System.out.println("Enter your password:");
                String password = scanner.nextLine().trim();
                try {
                    loginAndRegisterManager.loginUser(userName, password);
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        };
    }

}
