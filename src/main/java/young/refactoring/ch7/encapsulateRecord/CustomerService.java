package young.refactoring.ch7.encapsulateRecord;

public class CustomerService {

    public static ComparisonResult compareUsage(String customerId, int laterYear, int month) {
        double later = CustomerDataStore.getCustomerData().getUsage(customerId, laterYear, month);
        double earlier = CustomerDataStore.getCustomerData().getUsage(customerId, laterYear - 1, month);
        return new ComparisonResult(later, later - earlier);
    }

}