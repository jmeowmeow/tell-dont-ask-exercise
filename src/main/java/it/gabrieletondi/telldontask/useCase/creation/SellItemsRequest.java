package it.gabrieletondi.telldontask.useCase.creation;

import java.util.List;

public class SellItemsRequest {
    private List<SellItemRequest> requests;

    public void setRequests(List<SellItemRequest> requests) {
        this.requests = requests;
    }

    public List<SellItemRequest> getRequests() {
        return requests;
    }
}
