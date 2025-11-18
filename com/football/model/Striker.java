package com.football.model;

/**
 * Demonstrates:
 * - inheritance
 * - super() call
 * - overriding and polymorphism
 */
public final class Striker extends Player {
	
	//Parameterized constructor for Striker.
    public Striker(String name, int age, int goals) {
        super(name, age, goals); // super() → calling parent constructor i.e player
    }

    
    /**
     * Implementation of abstract method from Player.
     * Demonstrates polymorphism → each subclass has its own output.
     */
    @Override
    public void displayStats() {
        System.out.println("Striker " + getName() + " scored " + getScore() + " goals!");
    }
}
