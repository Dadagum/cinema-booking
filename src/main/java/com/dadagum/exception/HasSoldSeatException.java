package com.dadagum.exception;

public class HasSoldSeatException extends RuntimeException {
    public HasSoldSeatException() {
    }

    public HasSoldSeatException(String message) {
        super(message);
    }
}
