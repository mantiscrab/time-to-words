package com.example.timetowords;

import org.springframework.stereotype.Service;
import pl.allegro.finance.tradukisto.ValueConverters;

import java.time.LocalTime;

@Service
class TimeToWordsConverter {
    private final ValueConverters intConverter = ValueConverters.ENGLISH_INTEGER;
    private static final String MIDNIGHT = "midnight";
    private static final String NOON = "noon";
    private static final String O_CLOCK = "o'clock";
    private static final String QUARTER = "quarter";
    private static final String HALF = "half";
    private static final String PAST = "past";
    private static final String TO = "to";

    public String convert(final LocalTime time) {
        final int hour = time.getHour();
        final int minute = time.getMinute();

        if (minute == 0) {
            return formatPlainHour(hour);
        } else if (minute <= 30) {
            return formatLowerEqualThirtyMinutes(hour, minute);
        } else if (minute < 35) {
            return formatLowerThirtyFiveMinutes(hour, minute);
        } else {
            return formatBiggerEqualThirtyFiveMinutes(hour, minute);
        }
    }

    private String formatPlainHour(final int hour) {
        if (hour == 12)
            return NOON;
        if (hour == 0)
            return MIDNIGHT;
        return String.format("%s %s", intConverter.asWords(hour), O_CLOCK);
    }

    private String formatLowerEqualThirtyMinutes(final int hour, final int minute) {
        String hourString = intConverter.asWords(hour);
        String minuteString;
        if (minute == 15)
            minuteString = QUARTER;
        else if (minute == 30)
            minuteString = HALF;
        else
            minuteString = intConverter.asWords(minute);
        return String.format("%s %s %s", minuteString, PAST, hourString);
    }

    private String formatLowerThirtyFiveMinutes(final int hour, final int minute) {
        final String minuteString = intConverter.asWords(minute);
        return String.format("%s %s", intConverter.asWords(hour), minuteString);
    }

    private String formatBiggerEqualThirtyFiveMinutes(final int hour, final int minute) {
        final String minuteString = minute == 45 ? QUARTER : intConverter.asWords(60 - minute);
        final String hourString = intConverter.asWords(hour + 1);
        return String.format("%s %s %s", minuteString, TO, hourString);
    }
}

