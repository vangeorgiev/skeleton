package com.company.oop.cosmetics.exceptions.category;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
