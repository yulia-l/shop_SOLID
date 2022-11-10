import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Cart {
    private final UUID cartId;
    private String cartTitle;
    private final ArrayList<Product> productsInCart;

    public Cart() {
        this.cartId = UUID.randomUUID();
        this.productsInCart = new ArrayList<>();
    }

    public Cart(String cartTitle) {
        this.cartId = UUID.randomUUID();
        this.cartTitle = cartTitle;
        this.productsInCart = new ArrayList<>();
    }

    public UUID getCartId() {
        return cartId;
    }

    public String getCartTitle() {
        return Objects.requireNonNullElse(this.cartTitle, "Корзина");
    }

    public ArrayList<Product> getProductsInCart() {
        return productsInCart;
    }

    // Продукт с таким productId уже есть в Корзине?
    public boolean isProductInCart(UUID productId) {
        for (Product product : productsInCart) { // перебираем все продукты в Корзине
            if (productId.equals(product.getProductId())) return true;
        }
        return false;
    }

    // Вернуть Продукт из списка Продуктов в Корзине по productId
    public Product returnProductFromCartByProductId(UUID productId) {
        for (Product product : productsInCart) { // перебираем все продукты в Корзине
            if (productId.equals(product.getProductId())) {
                return product;
            }
        }
        return null;
    }


    // добавить Продукт в Корзину
    public void addProduct(Product product, int amount) {
        UUID productId = product.getProductId();
        if (isProductInCart(productId)) {
            int productCount = returnProductFromCartByProductId(productId).getInCartCount();
            returnProductFromCartByProductId(productId).setInCartCount(productCount + amount);
        } else {
            product.setInCartCount(amount);
            productsInCart.add(product);
        }
    }

    // показать продукты в Корзине
    public void showProductsInCart(boolean showProductId) {
        if (this.productsInCart.size() > 0) {
            // вывод шапки Корзины
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("|%-20s  |  %7s  |  %7s  |  %7s |%n", "Наименование товара", "Количество", "Цена/за ед.", "Общая стоимость");
            System.out.println("--------------------------------------------------------------------------");

            int total = 0;
            for (Product product : this.productsInCart) {
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
        } else {
            System.out.println("Нет Продуктов в Корзине!");
        }
    }

    // Вернуть сумму товаров в Корзине
    public int getCartSum() {
        int total = 0;
        for (Product product : this.productsInCart) {
            total += (product.getInCartCount() * product.getProductPrice());
        }
        return total;
    }

    // Очистить Корзину
    public boolean clearCart() {
        this.productsInCart.clear();
        return true;
    }

    @Override
    public String toString() {
        return "Cart { " +
                "cartId = " + cartId +
                ", productsInCart = " + productsInCart +
                " } ";
    }
}
