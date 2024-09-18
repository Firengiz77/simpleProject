package com.bookstore.simpleblog.exceptions;

public class SliderNotFoundException extends RuntimeException {
    private final static String MESSAGE = "Slider not found: ";


    public SliderNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
