package graphics.AdminProfile;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class ManageUsersMenu {
    public Parent parent;
    public Button deleteUserButton;
    public ListView usersList;

    private AdminProfileManager adminProfileManager;

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        ArrayList<Account> allUsers = adminProfileManager.getAllUsers();
        usersList.getItems().addAll(allUsers);
        usersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        deleteUserButton = new Button("Delete User");
        deleteUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedUsers = usersList.getSelectionModel().getSelectedIndices();

                for (Object selectedUser : selectedUsers) {
                    adminProfileManager.deleteUser(((Account) selectedUser).getUsername());
                }
            }
        });

        parent.getChildrenUnmodifiable().add(deleteUserButton);
    }
}
