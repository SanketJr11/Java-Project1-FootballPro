package com.football.model;

/**
 * Demonstrates:
 * - custom immutable type
 * - encapsulation
 */
public final class Statistics {
    private final int matches;
    private final int totalGoals;

    public Statistics(int matches, int totalGoals) {
        this.matches = matches;
        this.totalGoals = totalGoals;
    }

    public int getMatches() { return matches; }
    public int getTotalGoals() { return totalGoals; }

    public double average() {
        return matches == 0 ? 0 : (double) totalGoals / matches;
    }
}
