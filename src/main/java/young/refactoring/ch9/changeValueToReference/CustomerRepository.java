package young.refactoring.ch9.changeValueToReference;

import java.util.HashMap; //클래스
import java.util.Map; // 인터페이스

public class CustomerRepository {
    RepositoryData repositoryData;

    // initialize() 메서드가 싱글톤 객체의 필드를 초기화하는 메서드라면,
    // 여러 스레드에서 동시에 호출될 때 예상치 못한 결과가 발생할 수 있음
    public void initialize() {
        repositoryData = new RepositoryData();
        repositoryData.customer = new HashMap<>();
    }

    // has -> containsKey
    // set -> put
    // get == get
    public Customer registerCustomer(Long id) { // id overwrite 충돌 발생
        initialize();
        if (!repositoryData.customer.containsKey(id)) {
            repositoryData.customer.put(id, new Customer(id));
        }
        return findCustomer(id);
    }

    public Customer findCustomer(Long id) {
        return repositoryData.customer.get(id);
    }
}
