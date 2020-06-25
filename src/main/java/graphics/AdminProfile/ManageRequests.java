package graphics.AdminProfile;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import Model.Request.Request;
import graphics.AlertBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageRequests {

    private AdminProfileManager adminProfileManager;
    private static String parentMenu = "AdminProfileMenu";

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        manageRequests();
    }

    public void manageRequests() {
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
}
