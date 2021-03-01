package it.gabrieletondi.telldontask.repository;

import it.gabrieletondi.telldontask.domain.Order;

public interface OrderRepository {
    void save(Order order);

    Order getById(int orderId);
}
