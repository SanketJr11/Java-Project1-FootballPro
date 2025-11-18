package com.football.model;

/**
 * Another subclass demonstrating inheritance and polymorphism.
 */
public final class Goalkeeper extends Player {
    public Goalkeeper(String name, int age, int saves) {
        super(name, age, saves);
    }

    @Override
    public void displayStats() {
        System.out.println("🧤 Goalkeeper " + getName() + " made " + getScore() + " saves!");
    }
}
