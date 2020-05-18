package Model.Request;

import Model.Account.OffStatus;

import java.util.Date;

public abstract class EditAddOffRequest extends Request {
    protected String offID;
    protected Date startTime;
    protected Date endTime;
    protected int offAmount;
    protected OffStatus offStatus;

    public EditAddOffRequest(String requestID, RequestType requestType) {
        super(requestID, requestType);
    }

    public void setOffID(String offID) {
        this.offID = offID;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setOffAmount(int offAmount) {
        this.offAmount = offAmount;
    }

    public void setOffStatus(OffStatus offStatus) {
        this.offStatus = offStatus;
    }

    @Override
    public abstract void acceptRequest();

    @Override
    public abstract String toString();
}
