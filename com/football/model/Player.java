package com.football.model;

import java.time.LocalDate;

/**
 * Demonstrates:
 * - classes
 * - encapsulation
 * - contrast this() and this.
 * - inheritance (will be extended)
 * - overriding (abstract displayStats())
 * - use of Date API (LocalDate)
 * - sealed classes
 */
public sealed abstract class Player permits Striker, Goalkeeper , Defender {
    
	// Encapsulation: private fields
    private String name;
    private int age;
    private int score;
    private PlayerType type; // enum field
    private LocalDate joinDate;

    // Constructor chaining → contrast this() and this.
    public Player() {
        this("Unknown", 18, 0, PlayerType.UNKNOWN); // Calls another constructor in same class
    }
    
    /**
     * Parameterized constructor.
     * Called by subclasses to set common player attributes.
     */
    public Player(String name, int age, int score, PlayerType type) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.type = type;
        this.joinDate = LocalDate.now();
    }

    // Getters → encapsulation (read-only access to private fields)
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getScore() { return score; }
    public PlayerType getType() { return type;}
    public LocalDate getJoinDate() { return joinDate; }

    // Abstract method → to be overridden by subclasses i.e Striker and Goalkeeper (polymorphism)
    public abstract void displayStats();

    @Override   //Gives a human-readable representation of the player object.
    public String toString() {
        return name + " (" + age + ") - Score: " + score + " Joined: " + joinDate + " PlayerType: " + type ;
    }
}
