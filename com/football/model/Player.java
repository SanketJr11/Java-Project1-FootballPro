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
public sealed abstract class Player permits Striker, Goalkeeper {
    // Encapsulation: private fields
    private String name;
    private int age;
    private int score;
    private LocalDate joinDate;

    // Constructor chaining → contrast this() and this.
    public Player() {
        this("Unknown", 18, 0); // Calls another constructor in same class
    }

    public Player(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.joinDate = LocalDate.now();
    }

    // Getters → encapsulation
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getScore() { return score; }
    public LocalDate getJoinDate() { return joinDate; }

    // Abstract method → to be overridden by subclasses (polymorphism)
    public abstract void displayStats();

    @Override
    public String toString() {
        return name + " (" + age + ") - Score: " + score + " Joined: " + joinDate;
    }
}
