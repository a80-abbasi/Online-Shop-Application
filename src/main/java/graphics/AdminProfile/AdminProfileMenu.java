package graphics.AdminProfile;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import Model.Request.Request;
import graphics.AlertBox;

import graphics.App;
import graphics.LoginAndRegister.CreateAdminAccount;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminProfileMenu {
    private AdminProfileManager adminProfileManager;
    public TextField usernameField;
    public TextField passwordField;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField phoneNumberField;
    public Button confirmButton;

    private static String parentMenu;

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        usernameField.setText(adminProfileManager.getUsername());
        passwordField.setText(adminProfileManager.getPassword());
        firstNameField.setText(adminProfileManager.getFirstName());
        lastNameField.setText(adminProfileManager.getLastName());
        emailField.setText(adminProfileManager.getEmail());
        phoneNumberField.setText(adminProfileManager.getPhoneNumber());
    }

    public void confirm() {
        changePassword();
        changeFirstName();
        changeLastName();
        changeEmail();
        changePhoneNumber();
    }

    private void changePassword() {
        String password = passwordField.getText();
        try {
            adminProfileManager.editPassword(password);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit password", e.getMessage());
        }
    }

    private void changeFirstName() {
        String firstName = firstNameField.getText();
        try {
            adminProfileManager.editFirstName(firstName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit first name.", e.getMessage());
        }
    }

    private void changeLastName() {
        String lastName = lastNameField.getText();
        try {
            adminProfileManager.editLastName(lastName);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit last name", e.getMessage());
        }
    }

    private void changeEmail() {
        String email = emailField.getText();
        try {
            adminProfileManager.editEmail(email);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit email", e.getMessage());
        }
    }

    private void changePhoneNumber() {
        String phoneNumber = phoneNumberField.getText();
        try {
            adminProfileManager.editPhoneNumber(phoneNumber);
        } catch (IllegalArgumentException e) {
            AlertBox.showMessage("Failed to edit phone number", e.getMessage());
        }
    }

    public void manageUsers(MouseEvent mouseEvent) {
        TableView allUsersTable = adminProfileManager.getAllUsersTable();
        allUsersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Button deleteUserButton = new Button("Delete User");
        deleteUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedUsers = allUsersTable.getSelectionModel().getSelectedItems();

                for (Object selectedUser : selectedUsers) {
                    String selectedUsername = ((Account) selectedUser).getUsername();
                    adminProfileManager.deleteUser(selectedUsername);
                    AlertBox.showMessage("Delete User", "User with ID : <" + selectedUsername + "> deleted");
                    allUsersTable.getItems().remove(selectedUser);
                }
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(allUsersTable, deleteUserButton);
        Stage stage = new Stage();
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    public void addAdmin(MouseEvent mouseEvent) {
        CreateAdminAccount.setParentMenu("AdminProfileMenu");
        try {
            App.setRoot("CreateAdminAccount");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageRequests(MouseEvent mouseEvent) {
        TableView allRequestsTable = adminProfileManager.getAllRequestsTable();
        allRequestsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(allRequestsTable, acceptRequestButton(allRequestsTable), declineRequestButton(allRequestsTable), requestDetailsButton(allRequestsTable));
        Stage stage = new Stage();
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    private Button acceptRequestButton(TableView allRequestsTable) {
        Button acceptRequestButton = new Button("Accept Request");

        acceptRequestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedRequests = allRequestsTable.getSelectionModel().getSelectedItems();

                for (Object selectedRequest : selectedRequests) {
                    String selectedRequestID = ((Request) selectedRequest).getRequestId();
                    adminProfileManager.acceptRequest(selectedRequestID);
                    AlertBox.showMessage("Accept Request", "Request with ID : <" + selectedRequestID + "> accepted.");
                    allRequestsTable.getItems().remove(selectedRequest);
                }
            }
        });
        return acceptRequestButton;
    }

    private Button declineRequestButton(TableView allRequestsTable) {
        Button declineRequestButton = new Button("Decline Request");
        declineRequestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedRequests = allRequestsTable.getSelectionModel().getSelectedItems();

                for (Object selectedRequest : selectedRequests) {
                    String selectedRequestID = ((Request) selectedRequest).getRequestId();
                    adminProfileManager.declineRequest(selectedRequestID);
                    AlertBox.showMessage("Decline Request", "Request with ID : <" + selectedRequestID + "> declined.");
                    allRequestsTable.getItems().remove(selectedRequest);
                }
            }
        });
        return declineRequestButton;
    }

    //todo: completing this
    private Button requestDetailsButton(TableView allRequestsTable) {
        Button requestDetailsButton = new Button("Show Details");
        requestDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList selectedRequests = allRequestsTable.getSelectionModel().getSelectedItems();

                for (Object selectedRequest : selectedRequests) {
                    TableView requestDetails = adminProfileManager.getDetailsOfRequestTable(((Request) selectedRequest).getRequestId());
                    showTable(requestDetails);
                }
            }
        });
        return requestDetailsButton;
    }

    private void showTable(TableView tableView) {
        VBox vBox = new VBox();
        vBox.getChildren().add(tableView);
        Stage stage = new Stage();
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    //todo: adding delete button in productsMenu
    public void manageProducts(MouseEvent mouseEvent) {
        try {
            App.setRoot("ProductsMenu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageDiscountCodes(MouseEvent mouseEvent) {

    }

    public static void setParentMenu(String parentMenu) {
        AdminProfileMenu.parentMenu = parentMenu;
    }

    public void createDiscountCode(MouseEvent mouseEvent) {
        try {
            App.setRoot("CreateDiscountCode");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageCategories(MouseEvent mouseEvent) {

    }

    public void addCategory(MouseEvent mouseEvent) {

    }
}
