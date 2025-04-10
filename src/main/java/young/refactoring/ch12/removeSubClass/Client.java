package young.refactoring.ch12.removeSubClass;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public List<Person> loadFromInput(List<Data> dataList) {
        List<Person> result = new ArrayList<>();

        dataList.stream()
                .forEach(d -> {
                    Person p;
                    switch (d.gender) {
                        case "M": p = new Male(d.name); break;
                        case "F": p = new Female(d.name); break;
                        default: p = new Person(d.name);
                    }
                    result.add(p);
                });

        return result;
    }
}
