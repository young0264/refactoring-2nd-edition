package young.refactoring.ch11.replaceErrorCodeWithException;

public class OrderProcessingError extends Exception {

    private final String errorCode;

    public OrderProcessingError(String errorcode) {
        super(String.format("주문 처리 오류 : %s", errorcode));
        errorCode = errorcode;
    }

    public String getName(){
        return "OrderProcessingError";
    }

    public String getErrorCode() {
        return errorCode;
    }
}
