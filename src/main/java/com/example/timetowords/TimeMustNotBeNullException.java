package com.example.timetowords;

import lombok.Getter;

@Getter
class TimeMustNotBeNullException extends RuntimeException {
    private final String message = "Time must not be null";
}
