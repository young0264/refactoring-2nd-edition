package young.refactoring.ch11.replaceErrorCodeWithException;

import java.util.List;

public class Exam {

    private List<CustomError> errorList;
    private Order orderData;
    private CountryData countryData;

    public void exam() throws Exception {
        boolean status = false;
        try{
            status = calculateShippingCosts(orderData);
        } catch (Exception e){
            if (e instanceof OrderProcessingError) {
                errorList.add(new CustomError(orderData, new OrderProcessingError(112)));
            } else {
                throw e;
            }
        }
        if (!status) {
            errorList.add(new CustomError(orderData, new OrderProcessingError(-1)));
        }

    }

    public boolean calculateShippingCosts(Order anOrder) throws Exception {
        ShippingRules shippingRules = localShippingRules(anOrder.country);
        if (shippingRules == null ) throw new Exception("오류 코드가 다 사라지지 않았습니다."); //오류 전파
        //관련 없는 코드 ..
        return true;
    }

    public ShippingRules localShippingRules(String country) throws OrderProcessingError {

        String data = countryData.shippingRules.get(country);
        if(!data.isEmpty()) return new ShippingRules(data);
        else throw new OrderProcessingError(-23);
    }
}
