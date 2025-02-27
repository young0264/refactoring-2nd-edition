package young.refactoring.ch6.introduceParameterObject;

import java.util.List;
import java.util.stream.Collectors;

public class IntroduceParamObjExam {
    public List<Reading> readingOutsideRange(Station station, int min, int max) {
        return station.readings.stream()
                .filter(r -> r.temp < min || r.temp > max)
                .collect(Collectors.toList());
    }

}
