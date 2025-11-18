package com.football.service;

import com.football.model.Player;
import java.util.*;
import java.util.function.Predicate;

/**
 * Demonstrates:
 * - interfaces
 * - private, default, and static methods
 * - use of generic List (Java Core API)
 */
public interface PlayerService {
    void addPlayer(Player player);
    void listPlayers();
    Optional<Player> findPlayer(String name);
    List<Player> filterPlayers(java.util.function.Predicate<Player> condition); // Lambda support

    // Default method (Java 8+)
    default void welcome() {
        System.out.println("Welcome to Football Manager!");
    }

    // Static method
    static void separator() {
        System.out.println("--------------------------");
    }

    // Private method (Java 9+)
    private void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}
