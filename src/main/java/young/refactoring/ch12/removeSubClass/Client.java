package young.refactoring.ch12.removeSubClass;

import java.util.List;
import java.util.stream.Collectors;

public class Client {

    public List<Person> loadFromInput(List<Data> dataList) {
//        List<Person> result = new ArrayList<>();
//        dataList.stream()
//                .forEach(d -> {
//                    Person p = createPerson(d);
//                    result.add(p);
//                });
//        return result;

        return dataList.stream()
                .map(this::createPerson)
                .collect(Collectors.toList());

    }

    private Person createPerson(Data d) {
//        Person p;
//        switch (d.gender) {
//            case "M": p = new Male(d.name); break;
//            case "F": p = new Female(d.name); break;
//            default: p = new Person(d.name);
//        }
//        return p;
        return new Person(d.name, d.gender);
    }
}
