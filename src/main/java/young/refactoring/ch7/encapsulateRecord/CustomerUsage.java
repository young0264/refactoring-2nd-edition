package young.refactoring.ch7.encapsulateRecord;

import java.util.HashMap;
import java.util.Map;

public class CustomerUsage {
    private Map<Integer, Map<Integer, Double>> usages = new HashMap<>();

    public CustomerUsage() {}

    public CustomerUsage(CustomerUsage other) {
        for (Map.Entry<Integer, Map<Integer, Double>> yearEntry : other.usages.entrySet()) {
            Map<Integer, Double> monthMap = new HashMap<>(yearEntry.getValue());
            this.usages.put(yearEntry.getKey(), monthMap);
        }
    }

    public double getUsage(int year, int month) {
        return usages.getOrDefault(year, new HashMap<>())
                .getOrDefault(month, 0.0);
    }

    public void setUsage(int year, int month, double amount) {
        usages.computeIfAbsent(year, k -> new HashMap<>())
                .put(month, amount);
    }

//    public static ComparisonResult compareUsage(String customerId, int laterYear, int month) {
//        CustomerData customerData = CustomerDataStore.getCustomerData();
//
//        double later = customerData.getUsage(customerId, laterYear, month);
//        double earlier = customerData.getUsage(customerId, laterYear - 1, month);
//
//        return new ComparisonResult(later, later - earlier);
//    }
}
