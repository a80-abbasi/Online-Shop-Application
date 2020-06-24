package Model.Request;

import Model.Account.Off;
import Model.Account.OffStatus;
import Model.Product.Product;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class EditOffRequest extends EditAddOffRequest {
    private static ArrayList<EditOffRequest> allEditOffRequests = new ArrayList<>();
    private Off off;

    public EditOffRequest(Off off) {
        super("edit_off_" + allRequests.size(), RequestType.Editing_Off_Request);
        allEditOffRequests.add(this);
        this.setOff(off);
        this.setOffID(off.getOffID());
        this.setStartTime(off.getStartTime());
        this.setEndTime(off.getEndTime());
        this.setOffAmount(off.getOffAmount());
        this.setOffStatus(OffStatus.PENDING_FOR_EDITION);
        this.setOffProductIDs(off.getProductIDs());
    }

    public EditOffRequest() {
        this(null);
    }

    public static ArrayList<EditOffRequest> getAllEditOffRequests() {
        return allEditOffRequests;
    }

    public static void setAllEditOffRequests(ArrayList<EditOffRequest> allEditOffRequests) {
        EditOffRequest.allEditOffRequests = allEditOffRequests;
    }

    public void setOff(Off off) {
        this.off = off;
    }

    public void removeProduct(Product product) {
        offProductIDs.remove(product.getProductId());
    }

    @Override
    public void acceptRequest() throws IllegalArgumentException{
        if (Off.getOffById(offID) != null && (!(Off.getOffById(offID).equals(off)))) {
            throw new IllegalArgumentException();
        }
        else {
            for (String productID : off.getProductIDs()) {
                Product.getProductByID(productID).setOff(null);
            }
            off.setOffID(offID);
            off.setStartTime(startTime);
            off.setEndTime(endTime);
            off.setOffStatus(OffStatus.CONFIRMED);
            off.setOffAmount(offAmount);
            off.setProductsIDs(offProductIDs);
            for (String productID : offProductIDs) {
                Product.getProductByID(productID).setOff(off);
            }
        }
    }

    @Override
    public String toString() {
        return "EditOffRequest{" +
                "offID='" + offID + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", offAmount=" + offAmount +
                ", offStatus=" + offStatus +
                ", offProductIDs=" + offProductIDs +
                ", requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                '}';
    }

    @Override
    public TableView getRequestDetails() {
        TableView requestDetails = new TableView();

        TableColumn<String, Request> column1 = new TableColumn<>("Request ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("requestId"));

        TableColumn<RequestType, Request> column2 = new TableColumn<>("Request Type");
        column2.setCellValueFactory(new PropertyValueFactory<>("requestType"));

        //todo

        return requestDetails;
    }

    @Override
    public void remove() {
        allEditOffRequests.remove(this);
    }
}
