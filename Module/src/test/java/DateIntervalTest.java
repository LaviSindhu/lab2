import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateIntervalTest {

    // Three example intervals
    DateInterval interval1 = new DateInterval(
            new SimpleDate(2026, 1, 1),
            new SimpleDate(2026, 1, 5)
    );

    DateInterval interval2 = new DateInterval(
            new SimpleDate(2026, 1, 3),
            new SimpleDate(2026, 1, 10)
    );

    DateInterval interval3 = new DateInterval(
            new SimpleDate(2026, 2, 1),
            new SimpleDate(2026, 2, 5)
    );

    @Test
    void testDateIntervalDays() {
        assertEquals(4, DateInterval.dateIntervalDays(interval1));
    }

    @Test
    void testDateOverlap() {
        assertTrue(DateInterval.dateOverlap(interval1, interval2));
        assertFalse(DateInterval.dateOverlap(interval1, interval3));
    }

    @Test
    void testInvalidInterval() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DateInterval(
                        new SimpleDate(2026, 5, 10),
                        new SimpleDate(2026, 5, 5)
                )
        );
    }
    @Test
    void testDateIntervalIntersect() {
        assertEquals(
                new DateInterval(
                        new SimpleDate(2026, 1, 3),
                        new SimpleDate(2026, 1, 5)
                ),
                DateInterval.dateIntervalIntersect(interval1, interval2)
        );

        assertNull(
                DateInterval.dateIntervalIntersect(interval1, interval3)
        );
    }

    @Test
    void testMaybeDateIntervalIntersect() {
        assertNull(
                DateInterval.maybeDateIntervalIntersect(interval1, null)
        );

        assertEquals(
                new DateInterval(
                        new SimpleDate(2026, 1, 3),
                        new SimpleDate(2026, 1, 5)
                ),
                DateInterval.maybeDateIntervalIntersect(interval1, interval2)
        );
    }
}