package com.bookstore.simpleblog.exceptions;

public class CategoryNotFoundException extends  RuntimeException {

    private final static String MESSAGE = "Category not found";

    public CategoryNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
