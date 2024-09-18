package com.bookstore.simpleblog.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    private final static String MESSAGE = "Project not found: ";

    public ProjectNotFoundException(Long id) {
        super(MESSAGE+ id);
    }
}
