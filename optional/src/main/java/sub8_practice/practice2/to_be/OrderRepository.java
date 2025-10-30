package sub8_practice.practice2.to_be;

import sub8_practice.practice2.model.Delivery;
import sub8_practice.practice2.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepository {

    private static final Map<Long, Order> orders = new HashMap<>();

    public OrderRepository() {
        orders.put(1L, new Order(1L, new Delivery("DELIVERED", false)));
        orders.put(2L, new Order(2L, new Delivery("SHIPPED", false)));
        orders.put(3L, new Order(3L, new Delivery("SHIPPED", true)));
        orders.put(4L, new Order(3L, null));
    }

    public Optional<Order> findById(long id) {
        return Optional.ofNullable(orders.get(id));
    }
}
