public record DateInterval(SimpleDate start, SimpleDate end) {

    // Purpose: make sure the end date is not before the start date
    public DateInterval {
        if (!SimpleDate.comesBefore(start, end)) {
            throw new IllegalArgumentException("End date cannot come before start date");
        }
    }

    // Purpose: return the number of days in the interval
    static int dateIntervalDays(DateInterval interval) {
        int startDay =
                interval.start().year() * 365
                        + SimpleDate.dayOfYear(interval.start());

        int endDay =
                interval.end().year() * 365
                        + SimpleDate.dayOfYear(interval.end());

        return endDay - startDay;
    }

    // Purpose: return true if the two intervals overlap
    static boolean dateOverlap(DateInterval a, DateInterval b) {

        return SimpleDate.comesBefore(a.start(), b.end())
                && SimpleDate.comesBefore(b.start(), a.end())
                && !a.end().equals(b.start())
                && !b.end().equals(a.start());
    }
    // Purpose: return the intersection of two date intervals,
// or null if they do not overlap
    static DateInterval dateIntervalIntersect(DateInterval a, DateInterval b) {

        if (!dateOverlap(a, b)) {
            return null;
        }

        SimpleDate newStart;
        SimpleDate newEnd;

        if (SimpleDate.comesBefore(a.start(), b.start())) {
            newStart = b.start();
        } else {
            newStart = a.start();
        }

        if (SimpleDate.comesBefore(a.end(), b.end())) {
            newEnd = a.end();
        } else {
            newEnd = b.end();
        }

        return new DateInterval(newStart, newEnd);
    }


    // Purpose: return the intersection of two maybe-date-intervals,
// or null if either input is null or they do not overlap
    static DateInterval maybeDateIntervalIntersect(DateInterval a, DateInterval b) {

        if (a == null || b == null) {
            return null;
        }

        return dateIntervalIntersect(a, b);
    }
}