package young.refactoring.ch12.replaceSubclassWithDelegate.oneSubClass;

import java.time.LocalDateTime;

public class Booking {
    Show show;
    LocalDateTime date;
    PremiumBookingDelegate premiumBookingDelegate;

    private Booking createBooking(Show show, LocalDateTime date) {
        return new Booking(show, date);
    }

    public Booking(Show show, LocalDateTime date) {
        this.show = show;
        this.date = date;
    }

    public boolean hasTalkback() {
        return show.talkback && isPeakDay();
    }

    public int basePrice() {
        int result = show.price;
        if (isPeakDay()) result += Math.round(result * 0.15);
        return premiumBookingDelegate != null
                ? premiumBookingDelegate.extendPrice()
                : result;
    }



    public boolean isPeakDay() {
        return false;
    }

    public void bePremium(Extras extras) {
        premiumBookingDelegate = new PremiumBookingDelegate(extras, this);
    }

}
