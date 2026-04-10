package com.football;

import com.football.exception.PlayerNotFoundException;
import com.football.model.*;
import com.football.record.MatchRecord;
import com.football.record.PlayerSummary;
import com.football.service.AnalysisService;
import com.football.service.FileService;
import com.football.service.PlayerManager;
import com.football.util.FootballUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Locale locale = chooseLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("com.football.messages", locale);

        PlayerManager manager = new PlayerManager();
        FileService fileService = new FileService();
        AnalysisService analysisService = new AnalysisService();

        seedData(manager);

        boolean running = true;

        while (running) {
            printMenu(bundle);
            System.out.print(bundle.getString("menu.choice") + " ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addPlayer(manager, bundle);
                case "2" -> manager.listPlayersDetailed();
                case "3" -> searchPlayer(manager, bundle);
                case "4" -> addStatistics(manager, bundle);
                case "5" -> sortPlayers(manager, bundle);
                case "6" -> streamOperationsDemo(manager);
                case "7" -> collectOperationsDemo(manager);
                case "8" -> functionalInterfacesDemo(manager);
                case "9" -> patternMatchingDemo(manager);
                case "10" -> recordDemo(manager);
                case "11" -> dateTimeDemo(manager);
                case "12" -> savePlayers(manager, fileService, bundle);
                case "13" -> loadPlayers(manager, fileService, bundle);
                case "14" -> concurrentAnalysis(manager, analysisService);
                case "15" -> deletePlayer(manager, bundle);
                case "16" -> {
                    System.out.println(bundle.getString("app.goodbye"));
                    running = false;
                }
                default -> System.out.println(bundle.getString("menu.invalid"));
            }
        }
    }

        private static Locale chooseLocale() {
        System.out.println("Choose language / Roghnaigh teanga");
        System.out.println("1. English");
        System.out.println("2. Irish");
        System.out.print("Choice: ");
        String input = scanner.nextLine();

        return switch (input) {
            case "2" -> Locale.forLanguageTag("ga-IE");
            default -> Locale.ENGLISH;
        };
    }

    private static void printMenu(ResourceBundle bundle) {
        System.out.println("\n========== " + bundle.getString("menu.title") + " ==========");
        System.out.println("1. " + bundle.getString("menu.addPlayer"));
        System.out.println("2. " + bundle.getString("menu.listPlayers"));
        System.out.println("3. " + bundle.getString("menu.searchPlayer"));
        System.out.println("4. " + bundle.getString("menu.addStatistics"));
        System.out.println("5. Sort Players");
        System.out.println("6. Stream Operations Demo");
        System.out.println("7. Collectors Demo");
        System.out.println("8. Functional Interfaces Demo");
        System.out.println("9. Pattern Matching Demo");
        System.out.println("10. Record Demo");
        System.out.println("11. Date/Time Demo");
        System.out.println("12. Save Players to File");
        System.out.println("13. Load Players from File");
        System.out.println("14. Concurrent Analysis");
        System.out.println("15. " + bundle.getString("menu.deletePlayer"));
        System.out.println("16. " + bundle.getString("menu.exit"));
    }

    private static void seedData(PlayerManager manager) {
        manager.addPlayer(new Striker("Messi", 37, 25, LocalDate.of(2024, 1, 10)));
        manager.addPlayer(new Goalkeeper("Neuer", 36, 12, LocalDate.of(2023, 7, 5)));
        manager.addPlayer(new Defender("Ramos", 38, 15, LocalDate.of(2022, 3, 12)));
        manager.addPlayer(new Striker("Haaland", 24, 30, LocalDate.of(2025, 2, 1)));

        manager.findPlayer("Messi").ifPresent(p -> p.setStatistics(20, 25));
        manager.findPlayer("Neuer").ifPresent(p -> p.setStatistics(18, 12));
        manager.findPlayer("Ramos").ifPresent(p -> p.setStatistics(21, 15));
    }

    private static void addPlayer(PlayerManager manager, ResourceBundle bundle) {
        try {
            System.out.print(bundle.getString("prompt.name") + " ");
            String name = scanner.nextLine();

            System.out.print(bundle.getString("prompt.age") + " ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print(bundle.getString("prompt.type") + " ");
            String type = scanner.nextLine().trim().toUpperCase();

            System.out.print(bundle.getString("prompt.score") + " ");
            int score = Integer.parseInt(scanner.nextLine());

            Player player = switch (type) {
                case "STRIKER" -> new Striker(name, age, score);
                case "GOALKEEPER" -> new Goalkeeper(name, age, score);
                case "DEFENDER" -> new Defender(name, age, score);
                default -> throw new IllegalArgumentException("Invalid player type.");
            };

            manager.addPlayer(player);
            System.out.println(bundle.getString("player.added"));
        } catch (Exception e) {
            System.out.println(bundle.getString("error.invalidInput") + " " + e.getMessage());
        }
    }

    private static void searchPlayer(PlayerManager manager, ResourceBundle bundle) {
        System.out.print(bundle.getString("prompt.searchName") + " ");
        String name = scanner.nextLine();

        try {
            Player player = manager.findOrThrow(name);
            System.out.println(player);
        } catch (PlayerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addStatistics(PlayerManager manager, ResourceBundle bundle) {
        System.out.print(bundle.getString("prompt.searchName") + " ");
        String name = scanner.nextLine();

        Optional<Player> optionalPlayer = manager.findPlayer(name);
        if (optionalPlayer.isEmpty()) {
            System.out.println(bundle.getString("player.notFound"));
            return;
        }

        try {
            System.out.print("Enter total matches: ");
            int matches = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter total score/saves/tackles: ");
            int total = Integer.parseInt(scanner.nextLine());

            optionalPlayer.get().setStatistics(matches, total);
            System.out.println("Statistics updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println(bundle.getString("error.invalidInput"));
        }
    }

    private static void sortPlayers(PlayerManager manager, ResourceBundle bundle) {
        System.out.println("1. Sort by age");
        System.out.println("2. Sort by score descending");
        System.out.println("3. Sort by join date");
        System.out.print(bundle.getString("menu.choice") + " ");
        String option = scanner.nextLine();

        List<Player> result = switch (option) {
            case "1" -> manager.sortByAge();
            case "2" -> manager.sortByScoreDescending();
            case "3" -> manager.sortByJoinDate();
            default -> Collections.emptyList();
        };

        result.forEach(System.out::println);
    }

    private static void streamOperationsDemo(PlayerManager manager) {
        System.out.println("\n--- Stream Operations Demo ---");
        System.out.println("Total players: " + manager.countPlayers());
        System.out.println("Oldest player: " + manager.getOldestPlayer().orElse(null));
        System.out.println("Youngest player: " + manager.getYoungestPlayer().orElse(null));
        System.out.println("Find first striker: " + manager.findFirstStriker().orElse(null));
        System.out.println("Find any defender: " + manager.findAnyDefender().orElse(null));
        System.out.println("All players above 18: " + manager.allPlayersAboveAge(18));
        System.out.println("Any goalkeeper present: " + manager.anyGoalkeeper());
        System.out.println("No negative score: " + manager.noneWithNegativeScore());

        System.out.println("Distinct sorted names:");
        manager.getDistinctPlayerNames().forEach(System.out::println);

        System.out.println("Top 3 players by score:");
        manager.getTopNPlayers(3).forEach(System.out::println);
    }

    private static void collectOperationsDemo(PlayerManager manager) {
        System.out.println("\n--- toMap() Score ---");
        System.out.println(manager.getPlayerScoreMap());

        System.out.println("\n--- groupingBy() Type ---");
        System.out.println(manager.groupPlayersByType());

        System.out.println("\n--- partitioningBy() Adult ---");
        System.out.println(manager.partitionPlayersByAdult(30));
    }

    private static void functionalInterfacesDemo(PlayerManager manager) {
        System.out.println("\n--- Functional Interfaces Demo ---");

        Consumer<Player> printPlayer = p -> System.out.println("Consumer -> " + p.getName());
        Predicate<Player> adultPlayer = p -> p.getAge() >= 37;
        Function<Player, String> labelFunction = p -> p.getName() + " | " + p.getType();
        Supplier<Player> defaultPlayer = () -> new Striker("Default Striker", 20, 0);

        manager.getPlayerCopy().forEach(printPlayer);

        System.out.println("Adults only:");
        manager.filterPlayers(adultPlayer)
                .stream()
                .map(labelFunction)
                .forEach(System.out::println);

        System.out.println("Supplier created: " + defaultPlayer.get());
    }

    private static void patternMatchingDemo(PlayerManager manager) {
        System.out.println("\n--- Pattern Matching Demo ---");
        for (Player player : manager.getPlayerCopy()) {
            System.out.println(FootballUtils.describePlayerByPattern(player));
        }
    }

    private static void recordDemo(PlayerManager manager) {
        System.out.println("\n--- Record Demo ---");
        MatchRecord matchRecord = new MatchRecord("Real Madrid", 3, 1);
        System.out.println(matchRecord);

        List<PlayerSummary> summaries = manager.getPlayerSummaries();
        summaries.forEach(System.out::println);
    }

    private static void dateTimeDemo(PlayerManager manager) {
        System.out.println("\n--- Date/Time Demo ---");
        manager.getPlayerCopy().forEach(player ->
                System.out.println(player.getName() + " joined " +
                        FootballUtils.formatJoinDate(player.getJoinDate()) +
                        " | Days in club: " + FootballUtils.daysSince(player.getJoinDate()))
        );

        System.out.println("Average demo (varargs int): " + FootballUtils.average(10, 20, 30));
        System.out.println("Average demo (varargs double): " + FootballUtils.average(7.5, 8.5, 9.5));
    }

    private static void savePlayers(PlayerManager manager, FileService fileService, ResourceBundle bundle) {
        try {
            fileService.savePlayers(manager.getPlayerCopy(), "data/players.txt");
            System.out.println(bundle.getString("file.saved"));
        } catch (IOException e) {
            System.out.println(bundle.getString("file.error") + " " + e.getMessage());
        }
    }

    private static void loadPlayers(PlayerManager manager, FileService fileService, ResourceBundle bundle) {
        try {
            List<Player> loadedPlayers = fileService.loadPlayers("data/players.txt");
            loadedPlayers.forEach(manager::addPlayer);
            System.out.println(bundle.getString("file.loaded"));
        } catch (IOException e) {
            System.out.println(bundle.getString("file.error") + " " + e.getMessage());
        }
    }

    private static void concurrentAnalysis(PlayerManager manager, AnalysisService analysisService) {
        System.out.println("\n--- Concurrent Analysis Demo ---");
        List<String> results = analysisService.analysePlayers(manager.getPlayerCopy());
        results.forEach(System.out::println);
    }

    private static void deletePlayer(PlayerManager manager, ResourceBundle bundle) {
        System.out.print(bundle.getString("prompt.deleteName") + " ");
        String name = scanner.nextLine();

        try {
            manager.deletePlayer(name);
            System.out.println(bundle.getString("player.deleted"));
        } catch (PlayerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}