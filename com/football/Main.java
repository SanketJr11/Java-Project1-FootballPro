package com.football;

import com.football.model.*;
import com.football.service.*;
import com.football.record.*;
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
        var scanner = new Scanner(System.in);     // LVTI (Local Variable Type Inference)
        var manager = new PlayerManager();

        manager.addPlayer(new Striker("Messi", 37, 25));
        manager.addPlayer(new Goalkeeper("Neuer", 36, 12));

        boolean running = true;
        while (running) {
            System.out.println("""              
            	===== FOOTBALL MANAGER =====
                1. Add Player
                2. List Players
                3. Search Player
                4. Record Demo
                5. Average Demo (Varargs)
                6. Exit
            """);

            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            // Switch expressions (Java 21+)
            switch (choice) {
                case "1" -> {
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

                        Player p = switch (type.toUpperCase()) {
                            case "STRIKER" -> new Striker(name, age, score);
                            case "GOALKEEPER" -> new Goalkeeper(name, age, score);
                            case "DEFENDER" -> new Defender(name, age, score);
                            default -> null;
                        };

                        if (p != null) manager.addPlayer(p);
                    } catch (Exception e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                }

                case "2" -> manager.listPlayers();

                case "3" -> {
                    System.out.print("Enter player name: ");
                    String name = scanner.nextLine();
                    manager.findPlayer(name)
                        .ifPresentOrElse(System.out::println,
                            () -> System.out.println("Player not found!"));
                }

                case "4" -> {
                    MatchRecord record = new MatchRecord("Real Madrid", 3, 1);
                    System.out.println("Record Demo: " + record);
                }

                case "5" -> {
                    double avg = FootballUtils.average(10, 20, 30);
                    System.out.println("Average (varargs demo): " + avg);
                }

                case "6" -> running = false;

                default -> System.out.println("Invalid option!");
            }
        }
    }
}
