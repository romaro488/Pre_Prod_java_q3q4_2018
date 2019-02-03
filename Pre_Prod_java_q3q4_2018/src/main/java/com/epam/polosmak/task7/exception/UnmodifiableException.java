package com.epam.polosmak.task7.exception;

public class UnmodifiableException extends Throwable {

    public UnmodifiableException() {
    }

    public UnmodifiableException(String message) {
        super(message);
    }
}
