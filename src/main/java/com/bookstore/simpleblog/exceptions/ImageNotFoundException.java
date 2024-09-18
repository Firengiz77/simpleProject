package com.bookstore.simpleblog.exceptions;

public class ImageNotFoundException  extends  RuntimeException{
    private final static String MESSAGE = "Image Not Found: ";

    public ImageNotFoundException(Long id) {
        System.out.println(MESSAGE+ id);
    }

    public ImageNotFoundException(String name) {
        System.out.println(MESSAGE+ name);
    }

}
