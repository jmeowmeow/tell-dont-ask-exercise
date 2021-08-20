package it.gabrieletondi.telldontask.useCase.creation;

import java.util.ArrayList;
import java.util.List;

public class SellItemsRequest {

    private List<SellItemRequest> requests;

    public SellItemsRequest() {
        this.requests = new ArrayList<>();
    }

    public SellItemsRequest(List<SellItemRequest> requests) {
        this();
        this.requests.addAll(requests);
    }

    public void setRequests(List<SellItemRequest> requests) {
        this.requests = requests;
    }

    public List<SellItemRequest> getRequests() {
        return requests;
    }
}
