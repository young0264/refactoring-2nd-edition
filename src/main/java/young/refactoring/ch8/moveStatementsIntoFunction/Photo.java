package young.refactoring.ch8.moveStatementsIntoFunction;

import java.time.LocalDateTime;

public class Photo {
    String title;
    String location;
    LocalDateTime date;

    public Photo(String title, String location, LocalDateTime localDateTime) {
        this.title = title;
        this.location = location;
        this.date = localDateTime;
    }

}
