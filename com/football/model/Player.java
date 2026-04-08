package com.football.model;

import java.time.LocalDate;

public sealed abstract class Player permits Striker, Goalkeeper, Defender {

    private final String name;
    private final int age;
    private final int score;
    private final PlayerType type;
    private final LocalDate joinDate;
    private Statistics statistics;

    public Player() {
        this("Unknown", 18, 0, PlayerType.UNKNOWN, LocalDate.now());
    }

    public Player(String name, int age, int score, PlayerType type) {
        this(name, age, score, type, LocalDate.now());
    }

    public Player(String name, int age, int score, PlayerType type, LocalDate joinDate) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.type = type;
        this.joinDate = joinDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public PlayerType getType() {
        return type;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(int matches, int total) {
        this.statistics = new Statistics(matches, total);
    }

    public abstract void displayStats();

    @Override
    public String toString() {
        String statText = (statistics == null)
                ? "No stats"
                : "Matches=" + statistics.getMatches()
                + ", Total=" + statistics.getTotalValue()
                + ", Avg=" + String.format("%.2f", statistics.average());

        return name + " | Age=" + age + " | Score=" + score
                + " | Type=" + type + " | Joined=" + joinDate
                + " | " + statText;
    }
}