import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Product> productsInSale = new ArrayList<>(); // новый список Продуктов в продаже

        productsInSale.add(new Product("Хлеб", 40));
        productsInSale.add(new Product("Мука", 100));
        productsInSale.add(new Product("Молоко", 90));
        productsInSale.add(new Product("Мясо", 450));
        productsInSale.add(new Product("Рыба", 750));
        productsInSale.add(new Product("Соль", 30));
        productsInSale.add(new Product("Сахар", 60));

        Cart userCart = new Cart(); // новая Корзина

        User user = new User(); // новый пользователь

        user.setOrdersList(new ArrayList<>()); // добавляем список Заказов Пользователя


        // Главный цикл
        label:
        while (true) {

            Product.showProductList("\nСписок возможных товаров для покупки", productsInSale);
            actionList();

            System.out.println("Ваш выбор: ");
            String inputString = scanner.nextLine();

            switch (inputString) {
                case "end":
                    System.out.println("\nПрограмма завершена!");
                    System.out.println("\nВаша корзина:");
                    userCart.showProductsInCart(false); // показать Корзину

                    System.out.println("\nВаши заказы:");
                    user.showUserOrders(); // показать Заказы Пользователя

                    break label;
                case "1":
                    System.out.println(userCart.getCartTitle());
                    userCart.showProductsInCart(false);
                    break;
                case "2":
                    if (userCart.clearCart()) {
                        System.out.println("Корзина очищена!\n");
                    } else {
                        System.out.println("Ошибка очистки Корзины!\n");
                    }
                    break;
                case "3":
                    user.showUserOrders();
                    break;
                case "4":
                    if (userCart.getProductsInCart().size() > 0) {
                        user.addOrderInOrderList(new Order(userCart));
                        System.out.println("Оформлен новый Заказ на сумму " + userCart.getCartSum() + " руб.!");
                        userCart.clearCart();
                        System.out.println("Корзина очищена!\n");
                    } else {
                        System.out.println("Нет Продуктов в Корзине для оформления Заказа!");
                        System.out.println("Добавьте Продукты в Корзину!\n");
                    }

                    break;
                default:
                    String[] arr = inputString.split("\\s");
                    int productNumber = Integer.parseInt(arr[0]) - 1; // номер Продукта

                    int productCount = Integer.parseInt(arr[1]); // количество Продукта

                    // добавили товар в Корзину;
                    if (productNumber >= 0 && productNumber <= productsInSale.size()) {
                        userCart.addProduct(productsInSale.get(productNumber), productCount);
                        System.out.println("Продукт '" + productsInSale.get(productNumber).getProductName()
                                + "' в количестве " + productCount + " добавлен в Корзину!");
                    } else {
                        System.out.println("Нет товара с таким номером!");
                    }
                    break;
            }
        }
    }

    public static void actionList() {
        String actionList = "Выберите товар и количество или:\n" +
                "`1` - показать Корзину\n" +
                "`2` - очистить Корзину\n" +
                "------------------------------\n" +
                "`3` - показать Заказы\n" +
                "`4` - оформить Заказ\n" +
                "------------------------------\n" +
                "`end` - для выхода из программы\n";
        System.out.println(actionList);
    }
}
