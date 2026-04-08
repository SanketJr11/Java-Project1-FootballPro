package com.football.concurrency;

import com.football.model.Player;

import java.util.concurrent.Callable;

public class PlayerAnalysisTask implements Callable<String> {

    private final Player player;

    public PlayerAnalysisTask(Player player) {
        this.player = player;
    }

    @Override
    public String call() {
        String rating;
        if (player.getScore() >= 25) {
            rating = "Excellent";
        } else if (player.getScore() >= 15) {
            rating = "Good";
        } else {
            rating = "Average";
        }

        return "Concurrent analysis -> " + player.getName()
                + " | Type=" + player.getType()
                + " | Score=" + player.getScore()
                + " | Rating=" + rating;
    }
}