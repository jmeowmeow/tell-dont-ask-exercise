package it.gabrieletondi.telldontask.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private OrderStatus status = OrderStatus.CREATED;

    private BigDecimal total = new BigDecimal("0.00"); // Money?
    private String currency;

    private BigDecimal tax = new BigDecimal("0.00"); // Money?

    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(String currency) {
        this.currency = currency;
    }

    // add items
    // reject order
    // ship order

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
