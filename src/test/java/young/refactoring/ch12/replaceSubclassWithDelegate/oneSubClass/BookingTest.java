package young.refactoring.ch12.replaceSubclassWithDelegate.oneSubClass;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void 일반_예약은_basePrice_기본요금만_반영된다() {
        Show show = new Show(10000, true);
        Booking booking = new Booking(show, LocalDateTime.now());

        assertEquals(10000, booking.basePrice());
        assertFalse(booking.hasTalkback());
    }

    @Test
    void 프리미엄_예약은_basePrice_프리미엄요금_포함된다() {
        Show show = new Show(10000, true);
        Booking booking = new Booking(show, LocalDateTime.now());
        booking.bePremium(new Extras(true, 3000));

        assertEquals(13000, booking.basePrice());
    }

    @Test
    void 프리미엄은_hasDinner_기능을_사용할_수_있다() {
        Show show = new Show(8000, false);
        Booking booking = new Booking(show, LocalDateTime.now());
        Extras extras = new Extras(true, 2000);
        booking.bePremium(extras);

        assertTrue(booking.premiumBookingDelegate.hasDinner());
    }

    @Test
    void 일반예약은_delegate_null이므로_null체크_후_처리된다() {
        Show show = new Show(5000, false);
        Booking booking = new Booking(show, LocalDateTime.now());

        assertNull(booking.premiumBookingDelegate);
    }

}