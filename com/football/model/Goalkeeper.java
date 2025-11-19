package com.football.model;

/**
 * Another subclass demonstrating inheritance and polymorphism.
 */
public final class Goalkeeper extends Player {
	
	//Parameterized constructor for GoalKeeper.
    public Goalkeeper(String name, int age, int saves) {
        super(name, age, saves, PlayerType.GOALKEEPER);  // super() → calling parent constructor i.e player
    }

    
    /**
     * Implementation of abstract method from Player.
     * Demonstrates polymorphism → each subclass has its own output.
     */
    @Override
    public void displayStats() {
        System.out.println("Goalkeeper " + getName() + " made " + getScore() + " saves!");
    }
}
