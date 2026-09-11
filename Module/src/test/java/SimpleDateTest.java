import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleDateTest {


    SimpleDate date1 = new SimpleDate(2026, 1, 1);
    SimpleDate date2 = new SimpleDate(2026, 5, 15);
    SimpleDate date3 = new SimpleDate(2026, 12, 31);

    @Test
    void testDaysInMonth() {
        assertEquals(31, SimpleDate.daysInMonth(1));
        assertEquals(28, SimpleDate.daysInMonth(2));
        assertEquals(30, SimpleDate.daysInMonth(4));

        assertThrows(
                IllegalArgumentException.class,
                () -> SimpleDate.daysInMonth(13)
        );
    }

    @Test
    void testTomorrow() {
        assertEquals(
                new SimpleDate(2026, 5, 16),
                SimpleDate.tomorrow(date2)
        );

        assertEquals(
                new SimpleDate(2027, 1, 1),
                SimpleDate.tomorrow(date3)
        );
    }

    @Test
    void testDayOfYear() {
        assertEquals(
                0,
                SimpleDate.dayOfYear(date1)
        );

        assertEquals(
                134,
                SimpleDate.dayOfYear(date2)
        );
    }

    @Test
    void testComesBefore() {
        assertTrue(SimpleDate.comesBefore(date1, date2));

        assertFalse(SimpleDate.comesBefore(date3, date2));

        assertTrue(SimpleDate.comesBefore(date2, date2));

        assertFalse(
                SimpleDate.comesBefore(
                        new SimpleDate(2026, 6, 1),
                        new SimpleDate(2026, 5, 1)
                )
        );
    }

    @Test
    void testInvalidDate() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleDate(2026, 13, 1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleDate(2026, 2, 30)
        );
    }
    @Test
    void testTomorrowEndOfMonth() {
        assertEquals(
                new SimpleDate(2026, 6, 1),
                SimpleDate.tomorrow(new SimpleDate(2026, 5, 31))
        );
    }
    @Test
    void testComesBeforeMoreCases() {
        assertTrue(SimpleDate.comesBefore(
                new SimpleDate(2025, 12, 31),
                new SimpleDate(2026, 1, 1)
        ));

        assertFalse(SimpleDate.comesBefore(
                new SimpleDate(2026, 6, 1),
                new SimpleDate(2026, 5, 1)
        ));
    }
}