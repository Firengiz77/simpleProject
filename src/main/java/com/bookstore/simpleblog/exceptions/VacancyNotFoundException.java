package com.bookstore.simpleblog.exceptions;

public class VacancyNotFoundException extends RuntimeException{
    private final static String MESSAGE = "Vacancy Not Found: ";

    public VacancyNotFoundException(Long id) {
        super(MESSAGE+id);
    }
}
