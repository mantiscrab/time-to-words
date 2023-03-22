package com.example.timetowords;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
class UriProvider {
    private static final String LOCALHOST = "http://localhost";
    private final int localServerPort;


    public URI getUriOn(ResponseEntity<?> invocationInfo) {
        return baseMvcUriComponentsBuilder()
                .withMethodCall(invocationInfo)
                .build().toUri();
    }

    private MvcUriComponentsBuilder baseMvcUriComponentsBuilder() {
        return MvcUriComponentsBuilder.relativeTo(
                UriComponentsBuilder.fromUriString(baseUri())
        );
    }

    private String baseUri() {
        return LOCALHOST + ":" + localServerPort;
    }
}
