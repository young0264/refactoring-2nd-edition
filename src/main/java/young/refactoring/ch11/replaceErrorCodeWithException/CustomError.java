package young.refactoring.ch11.replaceErrorCodeWithException;

public class CustomError {
    private Order order;
    private OrderProcessingError errorCode;

    public CustomError(Order order, OrderProcessingError errorCode) {
        this.order = order;
        this.errorCode = errorCode;
    }

    public Order getOrder() {
        return order;
    }

    public OrderProcessingError getErrorCode() {
        return errorCode;
    }
}
