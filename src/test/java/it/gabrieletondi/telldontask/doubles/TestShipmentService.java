package it.gabrieletondi.telldontask.doubles;

import it.gabrieletondi.telldontask.domain.Order;
import it.gabrieletondi.telldontask.service.ShipmentService;

public class TestShipmentService implements ShipmentService {
    private Order shippedOrder = null;

    public Order getShippedOrder() {
        return shippedOrder;
    }

    @Override
    public void ship(Order order) {
        this.shippedOrder = order;
    }
}
