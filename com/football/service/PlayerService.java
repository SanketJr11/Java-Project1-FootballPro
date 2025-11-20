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
	
	// Basic CRUD-like operations
    void addPlayer(Player player);    // interface method (does not have a body)
    void listPlayers();
    Optional<Player> findPlayer(String name);
    List<Player> filterPlayers(java.util.function.Predicate<Player> condition); // Lambda support
    
    boolean deletePlayer(String name);  // to delete a player

    
    
    // Default method (Java 8+)
    //Provides a common implementation that can be used by implementors.
    
    default void welcome() {
        System.out.println("Welcome to Football Manager!");
    }

    // Static method
    static void separator() {
        System.out.println("--------------------------");
    }

    // Private method (Java 9+)
    //Can be reused by default methods inside the interface.
    private void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}
