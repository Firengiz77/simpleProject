package com.bookstore.simpleblog.exceptions;

public class ProductNotFoundException extends RuntimeException{
    private final static String MESSAGE = "Product not found: ";

    public ProductNotFoundException(Long id) {
        super(MESSAGE+id);
    }
}
