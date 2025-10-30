package sub8_practice.practice2.to_be;

import sub8_practice.practice2.model.Order;

import java.util.Optional;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrderBy(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
