package com.raphael.urlshortner.Exceptions;

public class NoSuchUrlExistsException extends RuntimeException {
    public NoSuchUrlExistsException(String message) {
        super(message);
    }
}
