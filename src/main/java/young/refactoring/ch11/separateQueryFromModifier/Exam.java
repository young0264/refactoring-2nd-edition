package young.refactoring.ch11.separateQueryFromModifier;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    List<Person> people = new ArrayList<>();

    public void alertForMiscreant() {
        if(findForMiscreant().equals("")) return;
        setOffAlarms();
    }

    public String findForMiscreant(){
        for (Person p : people) {
            if (p.name().equals("조커")) {
                return "조커";
            }
            if (p.name().equals("사루만")) {
                return "사루만";
            }
        }
        return "";
    }

    private void setOffAlarms() {

    }
}
