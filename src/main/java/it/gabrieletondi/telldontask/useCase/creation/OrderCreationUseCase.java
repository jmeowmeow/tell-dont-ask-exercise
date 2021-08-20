package it.gabrieletondi.telldontask.useCase.creation;

import it.gabrieletondi.telldontask.domain.Order;
import it.gabrieletondi.telldontask.domain.OrderItem;
import it.gabrieletondi.telldontask.domain.Product;
import it.gabrieletondi.telldontask.repository.OrderRepository;
import it.gabrieletondi.telldontask.repository.ProductCatalog;

import java.math.BigDecimal;
import java.util.Set;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class OrderCreationUseCase {
    private final OrderRepository orderRepository;
    private final ProductCatalog productCatalog;

    public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
        this.orderRepository = orderRepository;
        this.productCatalog = productCatalog;
    }

    public void run(SellItemsRequest request) {
        Order order = new Order("EUR");

        // assume that it is OK to repeatedly query product catalog (cached)
        validateProducts(request);

        for (SellItemRequest itemRequest : request.getRequests()) {
            Product product = productCatalog.getByName(itemRequest.getProductName());

            final BigDecimal unitaryTax = product.getPrice().divide(valueOf(100)).multiply(product.getCategory().getTaxPercentage()).setScale(2, HALF_UP);
            final BigDecimal unitaryTaxedAmount = product.getPrice().add(unitaryTax).setScale(2, HALF_UP);
            final BigDecimal taxedAmount = unitaryTaxedAmount.multiply(BigDecimal.valueOf(itemRequest.getQuantity())).setScale(2, HALF_UP);
            final BigDecimal taxAmount = unitaryTax.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            final OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setTax(taxAmount);
            orderItem.setTaxedAmount(taxedAmount);
            order.getItems().add(orderItem);

            order.setTotal(order.getTotal().add(taxedAmount));
            order.setTax(order.getTax().add(taxAmount));
        }

        orderRepository.save(order);
    }

    private void validateProducts(SellItemsRequest request) {
        Set<String> productNames = request.productNames();
        for (String productName : productNames) {
            Product product = productCatalog.getByName(productName);

            if (product == null) {
                throw new UnknownProductException();
            }
        }
    }
}
