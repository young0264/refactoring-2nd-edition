package young.refactoring.ch11.separteQueryFromModifier;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    List<Person> people = new ArrayList<>();

    public String alertForMiscreant() {
        for (Person p : people) {
            if (p.name().equals("조커")) {
                setOffAlarms();
                return "조커";
            }
            if (p.name().equals("사루만")) {
                setOffAlarms();
                return "사루만";
            }
        }
        return "";
    }

    private void setOffAlarms() {

    }
}
