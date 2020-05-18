package Model.Account;

import Model.Product.Product;

import java.util.ArrayList;
import java.util.Date;

public class Off {
    private static ArrayList<Off> allOffs = new ArrayList<>();
    private String offID;
    private Date startTime;
    private Date endTime;
    private int offAmount;
    private OffStatus offStatus;
    private ArrayList<Product> products;
    private static ArrayList<String> offFields = new ArrayList<>();
    static {
        offFields.add("offID");
        offFields.add("startTime");
        offFields.add("endTime");
        offFields.add("offAmount");
        offFields.add("offStatus");
        offFields.add("products");
    }

    public Off(String offID, Date startTime, Date endTime, int offAmount) {
        this.offID = offID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.offAmount = offAmount;
        this.offStatus = OffStatus.CONFIRMED;
        products = new ArrayList<>();
        allOffs.add(this);
    }

    public static void setAllOffs(ArrayList<Off> allOffs) {
        Off.allOffs = allOffs;
    }

    public Off() {
        this("", null, null, 0);
    }

    public String getOffID() {
        return offID;
    }

    public void setOffID(String offID) {
        this.offID = offID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getOffAmount() {
        return offAmount;
    }

    public void setOffAmount(int offAmount) {
        this.offAmount = offAmount;
    }

    public OffStatus getOffStatus() {
        return offStatus;
    }

    public void setOffStatus(OffStatus offStatus) {
        this.offStatus = offStatus;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public static ArrayList<Off> getAllOffs() {
        return allOffs;
    }

    public static ArrayList<String> getOffFields() {
        return offFields;
    }

    public static Off getOffById(String offID) {
        for (Off off : allOffs) {
            if (off.getOffID().equals(offID)) {
                return off;
            }
        }
        return null;
    }

    public static ArrayList<String> getAllOffsStatus() {
        ArrayList<String> allOffsStatus = new ArrayList<>();
        for (Off off : allOffs) {
            allOffsStatus.add(off.toString());
        }
        return allOffsStatus;
    }

    public static ArrayList<String> getAllOffIds() {
        ArrayList<String> allOffIds = new ArrayList<>();
        for (Off off : allOffs) {
            allOffIds.add(off.getOffID());
        }
        return allOffIds;
    }

    @Override
    public String toString() {
        return "Off{" +
                "offID='" + offID + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", offAmount=" + offAmount +
                ", offStatus=" + offStatus +
                ", products=" + products +
                '}';
    }
}
