package com.football.model;

import java.time.LocalDate;

public final class Defender extends Player {

    public Defender(String name, int age, int tackles) {
        super(name, age, tackles, PlayerType.DEFENDER);
    }

    public Defender(String name, int age, int tackles, LocalDate joinDate) {
        super(name, age, tackles, PlayerType.DEFENDER, joinDate);
    }

    @Override
    public void displayStats() {
        System.out.println("Defender " + getName() + " made " + getScore() + " tackles.");
    }
}