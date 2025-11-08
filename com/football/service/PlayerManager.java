package com.football.service;

import com.football.model.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * Demonstrates:
 * - implementation of interface
 * - overriding
 * - use of ArrayList (Core API)
 * - defensive copying
 * - lambda expressions and method references
 */
public class PlayerManager implements PlayerService {
    private final List<Player> players = new ArrayList<>();

    @Override
    public void addPlayer(Player p) {
        players.add(p);
    }

    @Override
    public void listPlayers() {
        if (players.isEmpty()) System.out.println("No players found!");
        // Method reference
        players.forEach(Player::displayStats);
    }

    @Override
    public Optional<Player> findPlayer(String name) {
        return players.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public List<Player> filterPlayers(Predicate<Player> condition) {
        return players.stream().filter(condition).toList(); // Lambda (Predicate)
    }

    // Defensive Copying (returns a new copy)
    public List<Player> getPlayerCopy() {
        return new ArrayList<>(players);
    }
}
