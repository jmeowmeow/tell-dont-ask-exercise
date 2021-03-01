package it.gabrieletondi.telldontask.service;

import it.gabrieletondi.telldontask.domain.Order;

public interface ShipmentService {
    void ship(Order order);
}
