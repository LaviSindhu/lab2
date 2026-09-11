public record DateList(SimpleDate first, DateList rest) {

    //this return the number of dates in the list
    static int listLen(DateList dates) {
        return switch (dates) {
            case null -> 0;
            case DateList(SimpleDate first, DateList rest) ->
                    1 + listLen(rest);
        };
    }


    // this return the earliest date in the list or null if the list is empty
    static SimpleDate minDate(DateList dates) {
        return switch (dates) {
            case null -> null;

            case DateList(SimpleDate first, DateList rest) -> {
                SimpleDate restMin = minDate(rest);

                if (restMin == null || SimpleDate.comesBefore(first, restMin)) {
                    yield first;
                }

                yield restMin;
            }
        };
    }



    // this returns the latest date in the list or null if the list is empty
    static SimpleDate maxDate(DateList dates) {
        return switch (dates) {
            case null -> null;

            case DateList(SimpleDate first, DateList rest) -> {
                SimpleDate restMax = maxDate(rest);

                if (restMax == null || SimpleDate.comesBefore(restMax, first)) {
                    yield first;
                }

                yield restMax;
            }
        };
    }


    // this returns the shortest interval containing every date
    static DateInterval dateCover(DateList dates) {
        return switch (dates) {
            case null -> null;

            case DateList(SimpleDate first, DateList rest) ->
                    new DateInterval(
                            minDate(dates),
                            SimpleDate.tomorrow(maxDate(dates))
                    );
        };
    }


    // this returns a new list where every date is changed to the next day
    static DateList allTomorrows(DateList dates) {
        return switch (dates) {
            case null -> null;

            case DateList(SimpleDate first, DateList rest) ->
                    new DateList(
                            SimpleDate.tomorrow(first),
                            allTomorrows(rest)
                    );
        };
    }


    // this adds a new date to the end of the list
    static DateList addToEnd(DateList dates, SimpleDate newDate) {
        return switch (dates) {
            case null ->
                    new DateList(newDate, null);

            case DateList(SimpleDate first, DateList rest) ->
                    new DateList(
                            first,
                            addToEnd(rest, newDate)
                    );
        };
    }


    // this returns all dates from the first list and followed by all dates from the second list
    static DateList append(DateList firstList, DateList secondList) {
        return switch (firstList) {
            case null -> secondList;

            case DateList(SimpleDate first, DateList rest) ->
                    new DateList(
                            first,
                            append(rest, secondList)
                    );
        };
    }
}
