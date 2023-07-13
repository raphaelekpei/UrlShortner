package com.raphael.urlshortner.Exceptions;

public class UrlExpirationException extends RuntimeException {
    public UrlExpirationException(String message) {
        super(message);
    }
}
