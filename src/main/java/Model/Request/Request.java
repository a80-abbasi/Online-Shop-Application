package Model.Request;

import java.util.ArrayList;

public class Request {
    protected static ArrayList<Request> allRequests;
    protected String requestId;

    static {
        allRequests = new ArrayList<>();
    }

    public Request(String requestId) {
        this.requestId = requestId;
        allRequests.add(this);
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getRequestId() {
        return requestId;
    }

    public void acceptRequest() {

    }

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
