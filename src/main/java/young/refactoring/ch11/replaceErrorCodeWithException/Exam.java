package young.refactoring.ch11.replaceErrorCodeWithException;

import java.util.List;

public class Exam {

    public List<CustomError> errorList;
    public Order orderData;
    public CountryData countryData;

    public void exam() throws Exception {
        try{
            calculateShippingCosts(orderData);
        } catch (Exception e){
            e.printStackTrace();
            if (e instanceof OrderProcessingError) {
                errorList.add(new CustomError(orderData, new OrderProcessingError(e.getMessage())));
            } else {
                throw e;
            }
        }
    }

    public void calculateShippingCosts(Order anOrder) throws Exception {
        ShippingRules shippingRules = localShippingRules(anOrder.country);
    }

    public ShippingRules localShippingRules(String country) throws OrderProcessingError {
        String data = countryData.shippingRules.get(country);
        if(data != null && !data.isEmpty()) return new ShippingRules(data);
        else throw new OrderProcessingError("-23"); //기존에는 -23을 return
    }
}
