package sub8_practice.practice2.to_be;

public class Controller {

    public static void main(String[] args) {

        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);

        System.out.println("1 = " + getDeliveryStatus(orderService, 1L));
        System.out.println("2 = " + getDeliveryStatus(orderService, 2L));
        System.out.println("3 = " + getDeliveryStatus(orderService, 3L));
        System.out.println("4 = " + getDeliveryStatus(orderService, 4L));
    }

    private static String getDeliveryStatus(OrderService orderService, long orderId) {

        return orderService.getOrderBy(orderId)
                .map(order -> order.getDelivery())
                .filter(delivery -> !delivery.isCanceled())
                .map(delivery -> delivery.getStatus())
                .orElseGet(() -> "CANCELED or delivery is null");
    }
}
