package young.refactoring.ch11.replaceParameterWithQuery;

/** 매개변수를 질의 함수로 바꾸기 */
public class Order {

    private int quantity;
    private int itemPrice;

    public Order(int quantity, int itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public int finalPrice() {
        int basePrice = quantity * itemPrice;
        return discountPrice(basePrice);
    }

    private int discountLevel() {
        return quantity > 100 ? 2 : 1;
    }

    private int discountPrice(int basePrice) {
        switch (discountLevel()) {
            case 1: return (int) (basePrice * 0.95);
            case 2: return (int) (basePrice * 0.90);
        }
        return 0;
    }
}
