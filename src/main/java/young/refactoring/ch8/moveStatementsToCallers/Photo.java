package young.refactoring.ch8.moveStatementsToCallers;

import java.time.LocalDateTime;

public class Photo {
    String title;
    String location;
    LocalDateTime date;

    public Photo(String title, String location, LocalDateTime date) {
        this.title = title;
        this.location = location;
        this.date = date;
    }

}
