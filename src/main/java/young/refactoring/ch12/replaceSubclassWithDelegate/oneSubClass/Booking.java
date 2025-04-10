package young.refactoring.ch12.replaceSubclassWithDelegate.oneSubClass;

import java.time.LocalDateTime;

public class Booking {
    Show show;
    LocalDateTime date;

    public Booking(Show show, LocalDateTime date) {
        this.show = show;
        this.date = date;
    }

    public int basePrice() {
        int result = show.price;
        if (isPeakDay()) result += Math.round(result * 0.15);
        return result;
    }

    public boolean hasTalkback() {
        return show.talkback && isPeakDay();
    }

    public boolean isPeakDay() {
        return false;
    }

}
