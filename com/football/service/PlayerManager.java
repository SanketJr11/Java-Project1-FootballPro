package com.football.service;

import com.football.model.*;
import com.football.exception.PlayerNotFoundException;
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
    private final List<Player> players = new ArrayList<>();  // Internal storage of players

    @Override
    public void addPlayer(Player p) {
        players.add(p);
    }

    @Override
    public void listPlayers() {
        if (players.isEmpty()) System.out.println("No players found!");
        
        players.forEach(Player::displayStats);   //Method reference
    }

    @Override
    public Optional<Player> findPlayer(String name) {
        return players.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
    }
    
    @Override
    public Player findOrThrow(String name) throws PlayerNotFoundException {
        return players.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new PlayerNotFoundException("Player '" + name + "' not found.")
                );
    }

    @Override
    public List<Player> filterPlayers(Predicate<Player> condition) {
    	// Functional style filtering using Predicate
        return players.stream().filter(condition).toList(); // Lambda (Predicate)
    }

    // Defensive Copying (returns a new copy) , cannot modify the internal list directly.
    public List<Player> getPlayerCopy() {
        return new ArrayList<>(players);
    }
    
    @Override
    public boolean deletePlayer(String name) throws PlayerNotFoundException {
        boolean removed = players.removeIf(p -> p.getName().equalsIgnoreCase(name));

        if (!removed) {
            throw new PlayerNotFoundException("Player '" + name + "' not found.");
        }

        return true; // success
    }
    
    
}
