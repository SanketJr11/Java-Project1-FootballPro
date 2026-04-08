package com.football.model;

import java.time.LocalDate;

public final class Striker extends Player {

    public Striker(String name, int age, int goals) {
        super(name, age, goals, PlayerType.STRIKER);
    }

    public Striker(String name, int age, int goals, LocalDate joinDate) {
        super(name, age, goals, PlayerType.STRIKER, joinDate);
    }

    @Override
    public void displayStats() {
        System.out.println("Striker " + getName() + " scored " + getScore() + " goals.");
    }
}