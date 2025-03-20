package young.refactoring.ch9.changeValueToReference;

public class Order {
    int number;
    Customer customer;

    // TO-BE: 특정 주문과 관련된 고객(Customer) 정보를 갱신하면,
    // 같은 고객(Customer)을 공유하는 주문(Order) 모두에서 갱신된 데이터를 사용하게 됨.
    public Order(int number, Long customerId) {
        CustomerRepository customerRepository = new CustomerRepository();
        this.number = number;
        this.customer = customerRepository.registerCustomer(customerId); // 존재하면 같은 customer를 바라봄
    }
}
