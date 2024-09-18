package com.bookstore.simpleblog.exceptions;

public class UsernameAlreadyException extends RuntimeException {
    private final static String MESSAGE = "Username already exists : ";

    public UsernameAlreadyException(String username) {
        super(MESSAGE + username);
    }
}
