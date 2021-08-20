package it.gabrieletondi.telldontask.doubles;

import it.gabrieletondi.telldontask.domain.Order;
import it.gabrieletondi.telldontask.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class TestOrderRepository implements OrderRepository, OrderRepositorySpy {
    private Order insertedOrder;
    private List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        this.insertedOrder = order;
    }

    @Override
    public Order getById(int orderId) {
        return orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
    }

    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public Order getSavedOrder() {
        return insertedOrder;
    }
}
