package young.refactoring.ch12.replaceSubclassWithDelegate.oneSubClass;

import java.time.LocalDateTime;

public class PremiumBooking extends Booking {
    Extras extras;

    public PremiumBooking(Show show, LocalDateTime date, Extras extras) {
        super(show, date);
        this.extras = extras;
    }

    @Override
    public boolean hasTalkback() {
        return true;
    }

    public boolean hasDinner() {
        return extras.dinner && !isPeakDay();
    }
}
