//package young.refactoring.ch12.replaceSubclassWithDelegate.oneSubClass;
//
//import java.time.LocalDateTime;
//
//public class PremiumBooking extends Booking {
//    Extras extras;
//
//    public PremiumBooking(Show show, LocalDateTime date, Extras extras) {
//        super(show, date);
//        this.extras = extras;
//    }
//
//    private PremiumBooking createPremiumBooking(Show show, LocalDateTime date, Extras extras) {
//        return new PremiumBooking(show, date, extras);
//    }
//
//    @Override
//    public boolean hasTalkback() {
//        return true;
//    }
//
//    // PremiumBooking 클래스
//    @Override
//    public int basePrice() {
//        return Math.round(super.basePrice() + extras.premiumFee);
//    }
//
//    @Override
//    public boolean isPeakDay() {
//        return true;
//    }
//
//    public boolean hasDinner() {
//        return extras.dinner && !isPeakDay();
//    }
//}
