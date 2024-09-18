package com.bookstore.simpleblog.exceptions;

public class CommentNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Comment not found";

    public CommentNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
