package com.bookstore.simpleblog.exceptions;

public class ContactNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Contact not found";

    public ContactNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
