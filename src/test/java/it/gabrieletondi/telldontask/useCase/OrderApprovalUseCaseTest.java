package it.gabrieletondi.telldontask.useCase;

import it.gabrieletondi.telldontask.domain.Order;
import it.gabrieletondi.telldontask.domain.OrderStatus;
import it.gabrieletondi.telldontask.doubles.TestOrderRepository;
import it.gabrieletondi.telldontask.useCase.approval.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontask.useCase.approval.OrderApprovalRequest;
import it.gabrieletondi.telldontask.useCase.approval.OrderApprovalUseCase;
import it.gabrieletondi.telldontask.useCase.approval.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontask.useCase.shipment.ShippedOrdersCannotBeChangedException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderApprovalUseCaseTest {
    private final TestOrderRepository orderRepository = new TestOrderRepository();
    private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

    @Test
    public void approvedExistingOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        useCase.run(request);

        final Order savedOrder = orderRepository.getSavedOrder();
        assertThat(savedOrder.getStatus())
            .isEqualByComparingTo(OrderStatus.APPROVED);
    }

    @Test
    public void rejectedExistingOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);

        useCase.run(request);

        final Order savedOrder = orderRepository.getSavedOrder();
        assertThat(savedOrder.getStatus())
            .isEqualByComparingTo(OrderStatus.REJECTED);
    }

    @Test
    public void cannotApproveRejectedOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.REJECTED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        assertThatThrownBy(() -> {
            useCase.run(request);
        }).isInstanceOf(RejectedOrderCannotBeApprovedException.class);

        assertThat(orderRepository.getSavedOrder())
            .isNull();
    }

    @Test
    public void cannotRejectApprovedOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.APPROVED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);

        assertThatThrownBy(() -> {
            useCase.run(request);
        }).isInstanceOf(ApprovedOrderCannotBeRejectedException.class);

        assertThat(orderRepository.getSavedOrder())
            .isNull();
    }

    @Test
    public void shippedOrdersCannotBeApproved() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.SHIPPED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        assertThatThrownBy(() -> {
            useCase.run(request);
        }).isInstanceOf(ShippedOrdersCannotBeChangedException.class);

        assertThat(orderRepository.getSavedOrder())
            .isNull();
    }

    @Test
    public void shippedOrdersCannotBeRejected() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setStatus(OrderStatus.SHIPPED);
        initialOrder.setId(1);
        orderRepository.addOrder(initialOrder);

        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);

        assertThatThrownBy(() -> {
            useCase.run(request);
        }).isInstanceOf(ShippedOrdersCannotBeChangedException.class);

        assertThat(orderRepository.getSavedOrder())
            .isNull();
    }
}
