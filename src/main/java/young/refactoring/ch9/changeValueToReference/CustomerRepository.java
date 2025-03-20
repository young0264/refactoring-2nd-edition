package young.refactoring.ch9.changeValueToReference;

import java.util.HashMap; //클래스
import java.util.Map; // 인터페이스

public class CustomerRepository {
    RepositoryData repositoryData;

    public void initialize() {
        repositoryData = new RepositoryData();
        repositoryData.customer = new HashMap<>();
    }

    // has -> containsKey
    // set -> put
    // get == get
    public void registerCustomer(Long id) {
        if (!repositoryData.customer.containsKey(id)) {
            repositoryData.customer.put(id, new Customer(id));
        }
    }

    public Customer findCustomer(Long id) {
        return repositoryData.customer.get(id);
    }
}
