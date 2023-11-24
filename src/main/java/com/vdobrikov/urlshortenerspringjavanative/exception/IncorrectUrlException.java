package com.vdobrikov.urlshortenerspringjavanative.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect URL")
public class IncorrectUrlException extends RuntimeException{
    private static final String MESSAGE_TEMPLATE = "Incorrect URL: %s";

    public IncorrectUrlException(String url) {
        super(String.format(MESSAGE_TEMPLATE, url));
    }
}
