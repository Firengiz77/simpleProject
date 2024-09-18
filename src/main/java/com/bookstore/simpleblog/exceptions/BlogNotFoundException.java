package com.bookstore.simpleblog.exceptions;

public class BlogNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Blog not found";

    public BlogNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
