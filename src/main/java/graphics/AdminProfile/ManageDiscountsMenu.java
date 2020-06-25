package graphics.AdminProfile;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import Model.Account.Discount;
import graphics.AlertBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Date;

public class ManageDiscountsMenu {
    public TextField includingCustomersField;
    public DatePicker startTimeDate;
    public TextField discountPerCustomerField;
    public TextField maxPossibleDiscountField;
    public TextField discountPercentField;
    public TextField discountCodeField;
    public DatePicker endTimeDate;

    public TableView allDiscountsTable;

    private ArrayList<String> includingCustomers;

    private static String parentMenu = "AdminProfileMenu";

    private AdminProfileManager adminProfileManager;

    public void initialize() {
        this.adminProfileManager = new AdminProfileManager((Admin) Account.getLoggedInAccount());
        allDiscountsTable = adminProfileManager.getAllDiscountsTable(allDiscountsTable);
    }

    public void showDetails() {

    }

    public void confirm(MouseEvent mouseEvent) {
        String discountCode = discountCodeField.getText();
        //todo: checking this
        Date startTime = new Date(startTimeDate.getValue().toEpochDay());
        Date endTime = new Date(endTimeDate.getValue().toEpochDay());
        String discountPercent = discountPercentField.getText();
        String maxPossibleDiscount = maxPossibleDiscountField.getText();
        String discountPerCustomer = discountPerCustomerField.getText();
        String includingCustomersFieldText = includingCustomersField.getText();
        boolean checkConfirmButtonInability;
        checkConfirmButtonInability = discountCode.isEmpty() || startTimeDate.getValue() == null || endTimeDate.getValue() == null || discountPercent.isEmpty() || maxPossibleDiscount.isEmpty() || discountPerCustomer.isEmpty() || includingCustomersFieldText.isEmpty();
        if (!(checkConfirmButtonInability)) {
            changeStartTime(discountCode, startTime);
            changeEndTime(discountCode, endTime);
            changeDiscountPercent(discountCode, discountPercent);
            changeMaxPossibleDiscount(discountCode, maxPossibleDiscount);
            changeIncludingCustomers(discountCode, includingCustomers);
        }
    }

    private void changeStartTime(String discountCode, Date newStartTime) {
        adminProfileManager.editDiscountStartTime(discountCode, newStartTime);
        AlertBox.showMessage("Edit", "Start Time Successfully Updated");
    }

    private void changeEndTime(String discountCode, Date newEndTime) {
        adminProfileManager.editDiscountEndTime(discountCode, newEndTime);
        AlertBox.showMessage("Edit", "End Time Successfully Updated");
    }

    private void changeDiscountPercent(String discountCode, String newDiscountPercent) {
        try {
            adminProfileManager.editDiscountPercent(discountCode, newDiscountPercent);
            AlertBox.showMessage("Edit", "Discount Percent Successfully Updated");
        } catch (Exception e) {
            AlertBox.showMessage("Failed Editing", e.getMessage());
        }
    }

    private void changeMaxPossibleDiscount(String discountCode, String maxPossibleDiscount) {
        try {
            adminProfileManager.editDiscountMaxPossibleDiscount(discountCode, maxPossibleDiscount);
            AlertBox.showMessage("Edit", "Maximum Possible Discount Successfully Updated");
        } catch (Exception e) {
            AlertBox.showMessage("Failed Editing", e.getMessage());
        }
    }

    private void changeIncludingCustomers(String discountCode, ArrayList<String> includingCustomers) {
        try {
            adminProfileManager.editDiscountIncludingCustomers(discountCode, includingCustomers);
            AlertBox.showMessage("Edit", "Including Customers Successfully Updated");
        } catch (Exception e) {
            AlertBox.showMessage("Failed Editing", e.getMessage());
        }
    }

    public void removeDiscount(MouseEvent mouseEvent) {
        Discount selectedDiscount = (Discount) allDiscountsTable.getSelectionModel().getSelectedItem();
        adminProfileManager.removeDiscount(selectedDiscount.getDiscountCode());
    }
}
