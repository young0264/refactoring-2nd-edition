package young.refactoring.ch12.removeSubClass;

import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public List<Person> loadFromInput(List<Data> dataList) {
        return dataList.stream()
                .map(this::createPerson)
                .collect(Collectors.toList());

    }

    private Person createPerson(Data d) {
        return new Person(d.name, d.gender);
    }
}
