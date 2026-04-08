package com.football.model;

import java.time.LocalDate;

public final class Goalkeeper extends Player {

    public Goalkeeper(String name, int age, int saves) {
        super(name, age, saves, PlayerType.GOALKEEPER);
    }

    public Goalkeeper(String name, int age, int saves, LocalDate joinDate) {
        super(name, age, saves, PlayerType.GOALKEEPER, joinDate);
    }

    @Override
    public void displayStats() {
        System.out.println("Goalkeeper " + getName() + " made " + getScore() + " saves.");
    }
}