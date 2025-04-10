package young.refactoring.ch12.replaceSubclassWithDelegate.oneSubClass;

import java.time.LocalDateTime;

public class Client {

    public void example(Show show, LocalDateTime date) {
        Booking booking = new Booking(show, date);
    }

//    public void example2(Show show, LocalDateTime date, Extras extras) {
//        PremiumBooking premiumBooking = new PremiumBooking(show, date, extras);
//    }
}
