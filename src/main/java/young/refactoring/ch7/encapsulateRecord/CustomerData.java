package young.refactoring.ch7.encapsulateRecord;

import java.util.HashMap;
import java.util.Map;

public class CustomerData {
    private Map<String, CustomerUsage> data;

    public CustomerData() {
    }

    public CustomerData(Map<String, CustomerUsage> data) {
        this.data = deepCopy(data); // 데이터를 안전하게 복사
    }

    public Map<String, CustomerUsage> getRawData() {
        return deepCopy(data);
    }

    public double getUsage(String customerId, int year, int month) {
        return data.getOrDefault(customerId, new CustomerUsage())
                .getUsage(year, month);
    }

    public void setUsage(String customerId, int year, int month, double amount) {
        data.computeIfAbsent(customerId, k -> new CustomerUsage())
                .setUsage(year, month, amount);
    }

    private Map<String, CustomerUsage> deepCopy(Map<String, CustomerUsage> original) {
        Map<String, CustomerUsage> copy = new HashMap<>();
        for (Map.Entry<String, CustomerUsage> entry : original.entrySet()) {
            copy.put(entry.getKey(), new CustomerUsage(entry.getValue()));
        }
        return copy;
    }

}