package com.example.timetowords;

import org.springframework.stereotype.Service;
import pl.allegro.finance.tradukisto.ValueConverters;

import java.time.LocalTime;

@Service
class TimeToWordsConverter {
    private final ValueConverters valueConverters = ValueConverters.ENGLISH_INTEGER;
    private static final String MIDNIGHT = "midnight";
    private static final String NOON = "noon";
    private static final String O_CLOCK = "o'clock";
    private static final String QUARTER = "quarter";
    private static final String HALF = "half";
    private static final String PAST = "past";
    private static final String TO = "to";

    public String convert(final LocalTime time) {
        if (time == null)
            throw new TimeMustNotBeNullException();
        return timeToWords(time);
    }

    private String timeToWords(LocalTime time) {
        final int hour = time.getHour();
        final int minute = time.getMinute();

        if (minute == 0)
            return formatPlainHour(hour);
        else if (minute <= 30)
            return formatLowerEqualThirtyMinutes(hour, minute);
        else if (minute < 35)
            return formatLowerThirtyFiveMinutes(hour, minute);
        else
            return formatBiggerEqualThirtyFiveMinutes(hour, minute);
    }

    private String formatPlainHour(final int hour) {
        if (hour == 12)
            return NOON;
        if (hour == 0)
            return MIDNIGHT;
        return String.format("%s %s", getTwelveHourBasedHour(hour), O_CLOCK);
    }

    private String formatLowerEqualThirtyMinutes(final int hour, final int minute) {
        final String hourString = getTwelveHourBasedHour(hour);
        final String minuteString = switch (minute) {
            case 15 -> QUARTER;
            case 30 -> HALF;
            default -> valueConverters.asWords(minute);
        };
        return String.format("%s %s %s", minuteString, PAST, hourString);
    }

    private String formatLowerThirtyFiveMinutes(final int hour, final int minute) {
        final String hourString = getTwelveHourBasedHour(hour);
        final String minuteString = valueConverters.asWords(minute);
        return String.format("%s %s", hourString, minuteString);
    }

    private String formatBiggerEqualThirtyFiveMinutes(final int hour, final int minute) {
        final String hourString = getTwelveHourBasedHour(hour + 1);
        final String minuteString = minute == 45 ? QUARTER : valueConverters.asWords(60 - minute);
        return String.format("%s %s %s", minuteString, TO, hourString);
    }

    private String getTwelveHourBasedHour(final int hour) {
        if (hour <= 12)
            return valueConverters.asWords(hour);
        return valueConverters.asWords(hour - 12);
    }
}

