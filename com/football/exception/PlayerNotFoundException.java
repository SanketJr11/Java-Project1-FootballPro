package com.football.exception;

/**
 * Demonstrates:
 * - checked exception (extends Exception)
 */
public class PlayerNotFoundException extends Exception {
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
