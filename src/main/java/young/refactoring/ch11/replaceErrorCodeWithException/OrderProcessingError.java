package young.refactoring.ch11.replaceErrorCodeWithException;

public class OrderProcessingError extends Exception {

    private final int errorCode;

    public OrderProcessingError(int errorcode) {
        super(String.format("주문 처리 오류 : %s", errorcode));
        errorCode = errorcode;
    }

    public String getName(){
        return "OrderProcessingError";
    }

}
