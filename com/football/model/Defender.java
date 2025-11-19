package com.football.model;

public final class Defender extends Player {

	//Parameterized constructor for Defender.
    public Defender(String name, int age, int goals) {
        super(name, age, goals, PlayerType.DEFENDER); // super() → calling parent constructor i.e player
    }

    
    /**
     * Implementation of abstract method from Player.
     * Demonstrates polymorphism → each subclass has its own output.
     */
    @Override
    public void displayStats() {
        System.out.println("Defender " + getName() + " has " + getScore() + " tackels!");
    }

}
 