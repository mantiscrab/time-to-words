package com.example.timetowords;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@AllArgsConstructor
@RestController
class TimeToWordsController {
    private final TimeToWordsConverter converter;

    @GetMapping("/convert")
    ResponseEntity<String> timeToWords(@RequestParam LocalTime time) {
        return ResponseEntity.ok(converter.convert(time));
    }
}
