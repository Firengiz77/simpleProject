package com.bookstore.simpleblog.exceptions;

public class VideoNotFoundException extends  RuntimeException{
    private final static String MESSAGE = "Video Not Found: ";

    public VideoNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
