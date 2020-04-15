package View;

import Controller.ProfileManager;

import java.util.ArrayList;

public class ViewPersonalInfoMenu extends Menu {
    private ProfileManager profileManager;

    public ViewPersonalInfoMenu(Menu parentMenu, ProfileManager profileManager) {
        super("View Personal Info Menu", parentMenu);
        this.profileManager = profileManager;
        ArrayList<Menu> submenus = new ArrayList<>();
        submenus.add(getEditFieldMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {
        String personalInfo = profileManager.viewPersonalInfo();
        System.out.println(personalInfo);
        super.show();
    }

    private Menu getEditFieldMenu() {
        return new Menu("Edit Field", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Enter field you want to edit or (Back) to return");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back")) {
                    this.parentMenu.show();
                    this.parentMenu.execute();
                }
                else {
                    System.out.println("Enter the change you want to make:");
                    String fieldChange = scanner.nextLine();
                    String fieldName = input;
                    profileManager.editFieldOfProfile(fieldName, fieldChange);
                    this.show();
                    this.execute();
                }
            }
        };
    }
}
