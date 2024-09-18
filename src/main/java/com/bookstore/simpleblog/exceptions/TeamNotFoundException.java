package com.bookstore.simpleblog.exceptions;

public class TeamNotFoundException extends RuntimeException{
    private final static String MESSAGE = "Team not found: ";

    public TeamNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
