package com.football;

import com.football.model.*;
import com.football.service.*;
import com.football.record.*;
import com.football.exception.PlayerNotFoundException;
import com.football.util.*;
import java.util.*;

/**
 * Demonstrates:
 * - use of LVTI (var)
 * - switch expressions and pattern matching
 * - lambdas
 * - exceptions handling
 * - arrays
 * - integration of all other features
 */
public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in); // LVTI (Local Variable Type Inference)
        var manager = new PlayerManager(); // LVTI - type is inferred as PlayerManager(implements PlayerService)
        
        // Pre-populate with a couple of players (for demo)
        manager.addPlayer(new Striker("Messi", 37, 25));
        manager.addPlayer(new Goalkeeper("Neuer", 36, 12));
        manager.addPlayer(new Defender("Ramos", 38, 15));

        boolean running = true;
        
        // Main loop – shows menu until user chooses to exit
        while (running) {
            System.out.println("""              
            ============== FOOTBALL MANAGER ===========
                1. Add Player
                2. List Players
                3. Search Player
                4. Add Player Statistics
                5. Record Demo
                6. Average Demo (Varargs)
                7. Delete player
                8. Exit
            """);

            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            // Switch expressions (Java 21+)
            switch (choice) {
                case "1" -> {     //Modern arrow (->) syntax , Cleaner than old switch
                	
                	// Add Player
                    try {
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        
                        System.out.print("Enter age: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Enter type (STRIKER/GOALKEEPER/DEFENDER): ");
                        String type = scanner.nextLine().trim();
                        
                        if (type.toUpperCase().equals("GOALKEEPER")) {
                        		System.out.print("Enter Saves: ");
                        		
	                        } else if (type.toUpperCase().equals("DEFENDER"))  {
	                    	   System.out.print("Enter Tackels: ");
	                    	   
	                        } else {
	                    	   System.out.print("Enter score: ");
	                        }
                    	   
                        int score = Integer.parseInt(scanner.nextLine());

                       // Switch expression returns the correct Player subclass
                        Player p = switch (type.toUpperCase()) {
                            case "STRIKER" -> new Striker(name, age, score);
                            case "GOALKEEPER" -> new Goalkeeper(name, age, score);
                            case "DEFENDER" -> new Defender(name, age, score);
                            default -> null;
                        };

                        if (p != null) manager.addPlayer(p);
                    } catch (Exception e) {
                    	// Catches any runtime issue here
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                }

                case "2" -> manager.listPlayers();  // List all players (polymorphic displayStats())

                case "3" -> {
                	System.out.print("Enter player name: ");
                    String name = scanner.nextLine();

                    try {
                        // Uses the new findOrThrow() from PlayerManager
                        Player player = manager.findOrThrow(name);
                        System.out.println(player);          // prints details + statistics
                        
                    } catch (PlayerNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                
                case "4" -> {
                    System.out.print("Enter player name: ");
                    String name = scanner.nextLine();

                    var optional = manager.findPlayer(name);

                    if(optional.isPresent()) {
                        Player player = optional.get();

                        System.out.print("Enter total matches: ");
                        int matches = Integer.parseInt(scanner.nextLine());

                        System.out.print("Enter total score (" +
                                player.getType() + " stats): ");
                        int total = Integer.parseInt(scanner.nextLine());

                        player.setStatistics(matches, total);

                        System.out.println("Statistics updated!");
                    } else {
                        System.out.println("Player not found.");
                    }
                }

                case "5" -> {
                	 // Simple demo of Java record type
                    MatchRecord record = new MatchRecord("Real Madrid", 3, 1); 
                    System.out.println("Record Demo: " + record);
                }

                case "6" -> {
                	 // Varargs + method overloading demo
                    double avg = FootballUtils.average(10, 20, 30);
                    System.out.println("Average (varargs demo): " + avg);
                }
                
                case "7" -> {
                	System.out.print("Enter player name to delete: ");
                    String delName = scanner.nextLine();

                    try {
                        manager.deletePlayer(delName);
                        System.out.println("Player '" + delName + "' deleted successfully.");
                    } catch (PlayerNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
               }

                case "8" -> running = false;   // Exit the loop / program

                default -> System.out.println("Invalid option!");
            }
        }
    }
}
