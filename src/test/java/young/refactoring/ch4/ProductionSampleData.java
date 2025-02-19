package young.refactoring.ch4;

import young.refactoring.ch4.model.Producer;
import young.refactoring.ch4.model.Province;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** [생산 계획]
 *  - 샘플 데이터 제공 클래스
 **/
public class ProductionSampleData {

    public static Province getSampleProvinceData() {
        ArrayList<Producer> producerList = Stream.of(
                new Producer("Seoul", 10, 9),
                new Producer("Busan", 12, 10),
                new Producer("Daegu", 10, 6)
        ).collect(Collectors.toCollection(ArrayList::new));
        return new Province("Asia", producerList, 30, 20);
    }

}
