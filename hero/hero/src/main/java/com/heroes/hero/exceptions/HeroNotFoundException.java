package com.heroes.hero.exceptions;

public class HeroNotFoundException extends RuntimeException{

    private static final long serialVersionUId = 1;

    public HeroNotFoundException(String message) {
        super(message);
    }
}
