package com.vdobrikov.urlshortenerspringjavanative.exception;

import com.vdobrikov.urlshortenerspringjavanative.persistence.UrlEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UrlEntityNotFoundException extends NotFoundException {

    public UrlEntityNotFoundException(String id) {
        super(id, UrlEntity.class);
    }

    public UrlEntityNotFoundException(UUID id) {
        super(id.toString(), UrlEntity.class);
    }
}
