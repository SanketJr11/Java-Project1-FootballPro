package com.football.model;

/**
 * Demonstrates:
 * - inheritance
 * - super() call
 * - overriding and polymorphism
 */
public final class Striker extends Player {
    public Striker(String name, int age, int goals) {
        super(name, age, goals); // super() → calling parent constructor
    }

    @Override
    public void displayStats() {
        System.out.println("⚽ Striker " + getName() + " scored " + getScore() + " goals!");
    }
}
