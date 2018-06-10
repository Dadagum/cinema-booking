package com.dadagum.exception;

public class FilmPeriodNotFoundException extends RuntimeException {

    public FilmPeriodNotFoundException() {
    }

    public FilmPeriodNotFoundException(String message) {
        super(message);
    }
}
