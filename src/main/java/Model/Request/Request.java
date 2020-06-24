package Model.Request;

import javafx.scene.control.TableView;

import java.util.ArrayList;

public abstract class Request {
    protected static ArrayList<Request> allRequests;
    protected String requestId;
    protected RequestType requestType;

    static {
        allRequests = new ArrayList<>();
    }

    public Request(String requestId, RequestType requestType) {
        this.requestId = requestId;
        this.requestType = requestType;
        allRequests.add(this);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getRequestId() {
        return requestId;
    }

    public abstract void acceptRequest();

    public abstract String toString();

    public abstract TableView getRequestDetails();

    public static Request getRequestById(String requestId) {
        for (Request request : allRequests) {
            if (request.getRequestId().equals(requestId)) {
                return request;
            }
        }
        return null;
    }

    public static void removeRequest(Request request) {
        allRequests.remove(request);
    }
}
