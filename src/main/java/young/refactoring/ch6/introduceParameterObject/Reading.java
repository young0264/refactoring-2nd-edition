package young.refactoring.ch6.introduceParameterObject;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reading {
    protected int temp;
    protected LocalDateTime time;

    public Reading(int temp) {
        this.temp = temp;
        this.time = LocalDateTime.now();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Reading reading = (Reading) o;
//        return temp == reading.temp && Objects.equals(time, reading.time);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reading reading = (Reading) o;
        return temp == reading.temp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp);
    }
}

//package young.refactoring.ch6.introduceParameterObject;
//
//        import java.util.Objects;
//
//public class Reading {
//    int temp;
//
//    public Reading(int temp) {
//        this.temp = temp;
//    }
//
//}
