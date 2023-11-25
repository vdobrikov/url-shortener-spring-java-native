package com.vdobrikov.urlshortenerspringjavanative.exception;

import com.vdobrikov.urlshortenerspringjavanative.persistence.UrlEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UrlEntityNotFoundException extends NotFoundException {
    private static final String MESSAGE_TEMPLATE = "Entity not found (%s). hash = %s";

    public UrlEntityNotFoundException(String hash) {
        super(String.format(MESSAGE_TEMPLATE, UrlEntity.class.getSimpleName(), hash));
    }
}
