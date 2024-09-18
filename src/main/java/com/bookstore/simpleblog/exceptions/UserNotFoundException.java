package com.bookstore.simpleblog.exceptions;

public class UserNotFoundException extends RuntimeException {
    private final static String MESSAGE = "User not found";

    public UserNotFoundException(Long id) {
        super((MESSAGE + id));
    }
}
