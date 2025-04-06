package young.refactoring.ch11.replaceCommandWithFunction;

/**
 * [명령을 함수로 바꾸기]
 * - 객체화할 이유가 없는 경우
 *   -> 클래스 분리가 오히려 가독성과 유지보수를 어렵게 함
 **/
public class Exam {

    public double client(Customer customer, int usage, Provider provider) {
        double monthCharge = charge(customer, usage, provider);
        return monthCharge;
    }

    private double charge(Customer customer, int usage, Provider provider) {
        return new ChargeCalculator(customer, usage, provider).charge();
    }
}
