package com.football.service;

import com.football.model.Player;
import com.football.exception.PlayerNotFoundException;
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
    Player findOrThrow(String name) throws PlayerNotFoundException;
    
    List<Player> filterPlayers(java.util.function.Predicate<Player> condition); // Lambda support
    
    boolean deletePlayer(String name) throws PlayerNotFoundException; // to delete a player


}
