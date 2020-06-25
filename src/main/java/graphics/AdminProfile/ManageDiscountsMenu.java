package graphics.AdminProfile;

import Controller.AdminProfileManager;
import Model.Account.Account;
import Model.Account.Admin;
import graphics.AlertBox;
import graphics.App;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
        String includingCustomers = includingCustomersField.getText();
        boolean checkConfirmButtonInability;
        checkConfirmButtonInability = discountCode.isEmpty() || startTimeDate.getValue() == null || endTimeDate.getValue() == null || discountPercent.isEmpty() || maxPossibleDiscount.isEmpty() || discountPerCustomer.isEmpty() || includingCustomers.isEmpty();
        if (!(checkConfirmButtonInability)) {

        }
    }

    private void changeStartTime() {

    }

    private void changeEndTime() {

    }

    private void changeDiscountPercent() {

    }

    public void removeDiscount(MouseEvent mouseEvent) {

    }
}
