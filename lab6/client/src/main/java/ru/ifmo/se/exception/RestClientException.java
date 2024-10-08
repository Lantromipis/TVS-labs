package ru.ifmo.se.exception;

import lombok.*;
import ru.ifmo.se.restclient.ErrorDto;

@Getter
public class RestClientException extends RuntimeException {
    private final ErrorDto error;

    public RestClientException(final ErrorDto error) {
        this.error = error;
    }
}
