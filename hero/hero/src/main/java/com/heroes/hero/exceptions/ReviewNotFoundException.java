package com.heroes.hero.exceptions;

public class ReviewNotFoundException extends RuntimeException {

    private static final long serialVersionUId = 1;

    public ReviewNotFoundException(String message) {
        super(message);
    }
}
