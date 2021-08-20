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

            // OrderItem.applyTax() ||  ProductPrice, Category - knows the %
            int quantity = itemRequest.getQuantity();

            // Product 120 ---> 120 / 100 --> 1.2 * 15 --> 18
            final BigDecimal taxPerSingleItem = product.calculateTax();

            // 120 + 18
            final BigDecimal priceWithTax = product.priceWithTax();

            final BigDecimal taxAmount = taxPerSingleItem.multiply(BigDecimal.valueOf(quantity));
            final BigDecimal taxedAmount = priceWithTax.multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);

            final OrderItem orderItem = new OrderItem(product, quantity);

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
            requireIsFound(product);
        }
    }

    private void requireIsFound(Product product) {
        if (product == null) {
            throw new UnknownProductException();
        }
    }
}
