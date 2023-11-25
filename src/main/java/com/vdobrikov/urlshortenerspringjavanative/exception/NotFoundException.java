package com.vdobrikov.urlshortenerspringjavanative.exception;

public class NotFoundException extends RuntimeException{
    private static final String MESSAGE_TEMPLATE = "Entity not found (%s). ID = %s";

    public NotFoundException(String id, Class<?> clazz) {
        super(String.format(MESSAGE_TEMPLATE, clazz.getSimpleName(), id));
    }

    public NotFoundException(String message) {
        super(message);
    }
}
