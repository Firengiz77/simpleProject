package com.bookstore.simpleblog.exceptions;

public class EmailAlreadyException extends RuntimeException {
    private  final  static String MESSAGE = "Email already exists : ";

    public EmailAlreadyException(String email) {
        super(MESSAGE+email);
    }
}
