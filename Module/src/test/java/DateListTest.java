import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateListTest {


    DateList list1 = new DateList(
            new SimpleDate(2026, 5, 5),
            null
    );

    DateList list2 = new DateList(
            new SimpleDate(2026, 5, 5),
            new DateList(
                    new SimpleDate(2026, 5, 10),
                    null
            )
    );

    DateList list3 = new DateList(
            new SimpleDate(2026, 5, 10),
            new DateList(
                    new SimpleDate(2026, 5, 2),
                    new DateList(
                            new SimpleDate(2026, 5, 7),
                            null
                    )
            )
    );


    @Test
    void testListLen() {
        assertEquals(0, DateList.listLen(null));
        assertEquals(1, DateList.listLen(list1));
        assertEquals(3, DateList.listLen(list3));
    }


    @Test
    void testMinDate() {
        assertEquals(
                new SimpleDate(2026, 5, 2),
                DateList.minDate(list3)
        );

        assertNull(DateList.minDate(null));
    }


    @Test
    void testDateCover() {
        assertEquals(
                new DateInterval(
                        new SimpleDate(2026, 5, 2),
                        new SimpleDate(2026, 5, 11)
                ),
                DateList.dateCover(list3)
        );
    }


    @Test
    void testAllTomorrows() {
        DateList expected = new DateList(
                new SimpleDate(2026, 5, 6),
                null
        );

        assertEquals(
                expected,
                DateList.allTomorrows(list1)
        );
    }


    @Test
    void testAddToEnd() {
        DateList expected = new DateList(
                new SimpleDate(2026, 5, 5),
                new DateList(
                        new SimpleDate(2026, 5, 8),
                        null
                )
        );

        assertEquals(
                expected,
                DateList.addToEnd(
                        list1,
                        new SimpleDate(2026, 5, 8)
                )
        );
    }


    @Test
    void testAppend() {
        DateList expected = new DateList(
                new SimpleDate(2026, 5, 5),
                new DateList(
                        new SimpleDate(2026, 5, 10),
                        null
                )
        );

        assertEquals(
                expected,
                DateList.append(list1,
                        new DateList(
                                new SimpleDate(2026, 5, 10),
                                null
                        )
                )
        );
    }
    @Test
    void testDateCoverEmpty() {
        assertNull(DateList.dateCover(null));
    }
}
