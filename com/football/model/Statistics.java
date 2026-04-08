package com.football.model;

public final class Statistics {
    private final int matches;
    private final int totalValue;

    public Statistics(int matches, int totalValue) {
        this.matches = matches;
        this.totalValue = totalValue;
    }

    public int getMatches() {
        return matches;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public double average() {
        return matches == 0 ? 0 : (double) totalValue / matches;
    }
}