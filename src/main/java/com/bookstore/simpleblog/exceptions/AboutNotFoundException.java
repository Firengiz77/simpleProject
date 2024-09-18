package com.bookstore.simpleblog.exceptions;

public class AboutNotFoundException extends RuntimeException {

    private final static String MESSAGE = "About not found: ";

    public AboutNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
