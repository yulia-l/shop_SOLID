import java.util.ArrayList;
import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private ArrayList<Order> ordersList;

    public User() {
        this.userId = UUID.randomUUID();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(ArrayList<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public void showUserOrders() {
        if (this.ordersList.size() > 0) {
            for (Order order : this.ordersList) {
                System.out.println("| " + order.getOrderNumber() + " | " + order.getOrderSum() + " руб. |");
            }
        } else {
            System.out.println("Нет оформленных заказов!");
        }
    }

    public void addOrderInOrderList(Order order) {
        this.ordersList.add(order);
    }
}
