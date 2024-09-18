package com.bookstore.simpleblog.exceptions;

public class FaqNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Faq not found";

    public FaqNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
