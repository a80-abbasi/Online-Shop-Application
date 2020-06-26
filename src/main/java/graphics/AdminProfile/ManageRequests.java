package graphics.AdminProfile;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import Model.Request.EditAddOffRequest;
import Model.Request.EditAddProductRequest;
import Model.Request.RegisterSellerRequest;
import Model.Request.Request;
import graphics.AdminProfile.RequestDetails.EditAddOffRequestMenu;
import graphics.AdminProfile.RequestDetails.EditAddProductRequestMenu;
import graphics.AdminProfile.RequestDetails.RegisterSellerRequestMenu;
import graphics.AlertBox;
import graphics.App;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ManageRequests {

    public TableView allRequestsTable;

    private AdminProfileManager adminProfileManager;

    private static String parentMenu = "AdminProfileMenu";

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        allRequestsTable = adminProfileManager.getAllRequestsTable(allRequestsTable);
    }

    public void acceptRequest(MouseEvent mouseEvent) {
        Object selectedRequest = allRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            return;
        }

        String selectedRequestID = ((Request) selectedRequest).getRequestId();
        adminProfileManager.acceptRequest(selectedRequestID);
        AlertBox.showMessage("Accept Request", "Request with ID : <" + selectedRequestID + "> accepted.");
        allRequestsTable.getItems().remove(selectedRequest);
    }

    public void showRequestDetails(MouseEvent mouseEvent) {
        Object selectedRequest = allRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            return;
        }
        Request request = (Request) selectedRequest;
        if (request instanceof EditAddOffRequest) {
            try {
                EditAddOffRequestMenu.setEditAddOffRequest(EditAddOffRequest.getRequestById(request.getRequestId()));
                App.setRoot("EditAddOffRequestMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (request instanceof EditAddProductRequest) {
            try {
                EditAddProductRequestMenu.setEditAddProductRequest(EditAddProductRequest.getRequestById(request.getRequestId()));
                App.setRoot("EditAddProductRequestMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (request instanceof RegisterSellerRequest) {
            try {
                RegisterSellerRequestMenu.setRegisterSellerRequest(RegisterSellerRequest.getRequestById(request.getRequestId()));
                App.setRoot("RegisterSellerRequestMenu");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void declineRequest(MouseEvent mouseEvent) {
        Object selectedRequest = allRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            return;
        }
        String selectedRequestID = ((Request) selectedRequest).getRequestId();
        adminProfileManager.declineRequest(selectedRequestID);
        AlertBox.showMessage("Decline Request", "Request with ID : <" + selectedRequestID + "> declined.");
        allRequestsTable.getItems().remove(selectedRequest);
    }
}
