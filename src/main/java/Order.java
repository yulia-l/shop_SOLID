import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final int orderNumber;
    private final int orderSum;
    private final Cart cartToOrder;
    private static int counter = 0;

    public Order(Cart cartToOrder) {
        this.orderId = UUID.randomUUID();
        this.orderNumber = counter;
        this.orderSum = cartToOrder.getCartSum();
        this.cartToOrder = cartToOrder;
        counter++;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getOrderSum() {
        return orderSum;
    }

    public Cart getCartToOrder() {
        return cartToOrder;
    }

    public void showOrderDetail() {
        System.out.println("Заказ №" + this.orderNumber);
        // вывод шапки Корзины
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("|%-20s  |  %7s  |  %7s  |  %7s |%n", "Наименование товара", "Количество", "Цена/за ед.", "Общая стоимость");
        System.out.println("--------------------------------------------------------------------------");

        int total = 0;

        for (Product product : this.getCartToOrder().getProductsInCart()) {
            System.out.printf(
                    "|%-20s  |  %10s  |  %11s  |  %15s |%n",
                    product.getProductName(),
                    product.getInCartCount(),
                    product.getProductPrice(),
                    (product.getInCartCount() * product.getProductPrice())
            );
            total += (product.getInCartCount() * product.getProductPrice());
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%73s", "Итого " + total + " руб.");
    }
}
