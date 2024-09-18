package com.bookstore.simpleblog.exceptions;

public class TagNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Tag not found";

    public TagNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
