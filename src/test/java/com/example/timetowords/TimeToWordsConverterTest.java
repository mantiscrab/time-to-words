package com.example.timetowords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

class TimeToWordsConverterTest {
    TimeToWordsConverter converter;

    @BeforeEach
    void initConverter() {
        converter = new TimeToWordsConverter();
    }

    public static Stream<Arguments> dateToWordsSource() {
        return Stream.of(
                Arguments.of(LocalTime.of(1, 0), "one o'clock"),
                Arguments.of(LocalTime.of(2, 5), "five past two"),
                Arguments.of(LocalTime.of(3, 10), "ten past three"),
                Arguments.of(LocalTime.of(4, 15), "quarter past four"),
                Arguments.of(LocalTime.of(5, 20), "twenty past five"),
                Arguments.of(LocalTime.of(6, 25), "twenty-five past six"),
                Arguments.of(LocalTime.of(6, 32), "six thirty-two"),
                Arguments.of(LocalTime.of(7, 30), "half past seven"),
                Arguments.of(LocalTime.of(7, 35), "twenty-five to eight"),
                Arguments.of(LocalTime.of(8, 40), "twenty to nine"),
                Arguments.of(LocalTime.of(9, 45), "quarter to ten"),
                Arguments.of(LocalTime.of(10, 50), "ten to eleven"),
                Arguments.of(LocalTime.of(11, 55), "five to twelve"),
                Arguments.of(LocalTime.of(0, 0), "midnight"),
                Arguments.of(LocalTime.of(12, 0), "noon"),
                Arguments.of(LocalTime.of(13, 0), "one o'clock"),
                Arguments.of(LocalTime.of(14, 5), "five past two"),
                Arguments.of(LocalTime.of(15, 10), "ten past three"),
                Arguments.of(LocalTime.of(16, 15), "quarter past four"),
                Arguments.of(LocalTime.of(17, 20), "twenty past five"),
                Arguments.of(LocalTime.of(18, 25), "twenty-five past six"),
                Arguments.of(LocalTime.of(18, 32), "six thirty-two"),
                Arguments.of(LocalTime.of(19, 30), "half past seven"),
                Arguments.of(LocalTime.of(19, 35), "twenty-five to eight"),
                Arguments.of(LocalTime.of(20, 40), "twenty to nine"),
                Arguments.of(LocalTime.of(21, 45), "quarter to ten"),
                Arguments.of(LocalTime.of(22, 50), "ten to eleven"),
                Arguments.of(LocalTime.of(23, 55), "five to twelve")
        );
    }

    @ParameterizedTest
    @MethodSource("dateToWordsSource")
    void shouldConvertDateToWords(LocalTime time, String words) {
        Assertions.assertEquals(words, converter.convert(time));
    }

    @Test
    void shouldThrowExceptionWhenTimeIsNull() {
        Assertions.assertThrows(TimeMustNotBeNullException.class,
                () -> converter.convert(null));
    }
}