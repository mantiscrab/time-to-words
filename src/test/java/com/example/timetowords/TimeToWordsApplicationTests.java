package com.example.timetowords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalTime;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TimeToWordsApplicationTests {
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate restTemplate;
    UriProvider uriProvider;

    @BeforeEach
    void init() {
        uriProvider = new UriProvider(port);
    }

    @Test
    void shouldConvertTimeToWords() {
        //when
        final URI uri = getConvertTimeToWordsUri(LocalTime.of(15, 47));
        final ResponseEntity<String> timeToWords = restTemplate.getForEntity(uri, String.class);
        //then
        Assertions.assertEquals(HttpStatus.OK, timeToWords.getStatusCode());
        Assertions.assertEquals("thirteen to sixteen", timeToWords.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenTimeIsNull() {
        //when
        final URI uri = getConvertTimeToWordsUri(null);
        final ResponseEntity<String> timeToWords = restTemplate.getForEntity(uri, String.class);
        //then
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, timeToWords.getStatusCode());
    }

    private URI getConvertTimeToWordsUri(LocalTime time) {
        return uriProvider.getUriOn(on(TimeToWordsController.class).convertTimeToWords(time));
    }
}
