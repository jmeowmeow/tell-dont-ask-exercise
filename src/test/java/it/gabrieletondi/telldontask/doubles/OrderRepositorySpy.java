package it.gabrieletondi.telldontask.doubles;

import it.gabrieletondi.telldontask.domain.Order;

public interface OrderRepositorySpy {

    void addOrder(Order order);

    Order getSavedOrder();
}
