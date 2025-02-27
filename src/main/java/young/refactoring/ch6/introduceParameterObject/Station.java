package young.refactoring.ch6.introduceParameterObject;

import java.util.List;

public class Station {
    String name;
    List<Reading> readings;

    public Station(String name, List<Reading> readings) {
        this.name = name;
        this.readings = readings;
    }
}
