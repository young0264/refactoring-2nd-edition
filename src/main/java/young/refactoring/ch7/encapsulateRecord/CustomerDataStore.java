package young.refactoring.ch7.encapsulateRecord;

public class CustomerDataStore {

    private static CustomerData customerData;

    public static CustomerData getCustomerData() {
        return customerData;
    }

    public static void setRawDataOfCustomers(CustomerData newData) {
        customerData = newData;
    }

}