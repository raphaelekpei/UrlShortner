package com.raphael.urlshortner.Exceptions;

public class UnableToCreateUrlException extends RuntimeException {
    public UnableToCreateUrlException(String message) {
        super(message);
    }
}
