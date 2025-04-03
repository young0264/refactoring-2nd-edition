package young.refactoring.ch11.replaceParameterWithQuery;

public class Order {

    private int quantity;
    private int itemPrice;

    public int finalPrice() {
        int basePrice = quantity * itemPrice;
        int discountLevel = discountLevel();
        return discountPrice(basePrice, discountLevel);
    }

    private int discountLevel() {
        return quantity > 100 ? 2 : 1;
    }

    private int discountPrice(int basePrice, int discountLevel) {
        switch (discountLevel) {
            case 1: return (int) (basePrice * 0.95);
            case 2: return (int) (basePrice * 0.90);
        }
        return 0;
    }
}
