package it.gabrieletondi.telldontask.useCase.shipment;

public class OrderShipmentRequest {
    private int orderId;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
