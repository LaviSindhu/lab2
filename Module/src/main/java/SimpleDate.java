public record SimpleDate(int year, int month, int day) {

    public SimpleDate {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month");
        }

        if (day < 1 || day > daysInMonth(month)) {
            throw new IllegalArgumentException("Invalid day");
        }
    }

    // Purpose: return the number of days in the given month
    static int daysInMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month");
        }

        if (month == 2) {
            return 28;
        }

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }

        return 31;
    }

    // Purpose: return the date that comes one day after the given date
    static SimpleDate tomorrow(SimpleDate date) {
        if (date.day() < daysInMonth(date.month())) {
            return new SimpleDate(
                    date.year(),
                    date.month(),
                    date.day() + 1
            );
        }

        if (date.month() == 12) {
            return new SimpleDate(
                    date.year() + 1,
                    1,
                    1
            );
        }

        return new SimpleDate(
                date.year(),
                date.month() + 1,
                1
        );
    }

    // Purpose: return the number of days that have passed since January 1
    static int dayOfYear(SimpleDate date) {
        int totalDays = 0;

        for (int month = 1; month < date.month(); month++) {
            totalDays = totalDays + daysInMonth(month);
        }

        return totalDays + date.day() - 1;
    }

    // Purpose: return true if date1 is before or the same as date2
    static boolean comesBefore(SimpleDate date1, SimpleDate date2) {
        if (date1.year() < date2.year()) {
            return true;
        }

        if (date1.year() > date2.year()) {
            return false;
        }

        if (date1.month() < date2.month()) {
            return true;
        }

        if (date1.month() > date2.month()) {
            return false;
        }

        return date1.day() <= date2.day();
    }
}