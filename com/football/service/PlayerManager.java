package com.football.service;

import com.football.exception.PlayerNotFoundException;
import com.football.model.*;
import com.football.record.PlayerSummary;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PlayerManager implements PlayerService {

    private final List<Player> players = new ArrayList<>();

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void listPlayers() {
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }
        players.forEach(Player::displayStats);
    }

    public void listPlayersDetailed() {
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }
        players.forEach(System.out::println);
    }

    @Override
    public Optional<Player> findPlayer(String name) {
        return players.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public Player findOrThrow(String name) throws PlayerNotFoundException {
        return players.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException("Player '" + name + "' not found."));
    }

    @Override
    public List<Player> filterPlayers(Predicate<Player> condition) {
        return players.stream()
                .filter(condition)
                .toList();
    }

    @Override
    public boolean deletePlayer(String name) throws PlayerNotFoundException {
        boolean removed = players.removeIf(p -> p.getName().equalsIgnoreCase(name));
        if (!removed) {
            throw new PlayerNotFoundException("Player '" + name + "' not found.");
        }
        return true;
    }

    public List<Player> getPlayerCopy() {
        return new ArrayList<>(players);
    }

    // Sorting using Comparator.comparing()
    public List<Player> sortByAge() {
        return players.stream()
                .sorted(Comparator.comparing(Player::getAge))
                .toList();
    }

    public List<Player> sortByScoreDescending() {
        return players.stream()
                .sorted(Comparator.comparing(Player::getScore).reversed())
                .toList();
    }

    public List<Player> sortByJoinDate() {
        return players.stream()
                .sorted(Comparator.comparing(Player::getJoinDate))
                .toList();
    }

    // Terminal operations
    public Optional<Player> getOldestPlayer() {
        return players.stream().max(Comparator.comparing(Player::getAge));
    }

    public Optional<Player> getYoungestPlayer() {
        return players.stream().min(Comparator.comparing(Player::getAge));
    }

    public long countPlayers() {
        return players.stream().count();
    }

    public Optional<Player> findAnyDefender() {
        return players.stream()
                .filter(p -> p.getType() == PlayerType.DEFENDER)
                .findAny();
    }

    public Optional<Player> findFirstStriker() {
        return players.stream()
                .filter(p -> p.getType() == PlayerType.STRIKER)
                .findFirst();
    }

    public boolean allPlayersAboveAge(int age) {
        return players.stream().allMatch(p -> p.getAge() > age);
    }

    public boolean anyGoalkeeper() {
        return players.stream().anyMatch(p -> p.getType() == PlayerType.GOALKEEPER);
    }

    public boolean noneWithNegativeScore() {
        return players.stream().noneMatch(p -> p.getScore() < 0);
    }

    // Intermediate operations
    public List<String> getDistinctPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .distinct()
                .sorted()
                .toList();
    }

    public List<Player> getTopNPlayers(int n) {
        return players.stream()
                .sorted(Comparator.comparing(Player::getScore).reversed())
                .limit(n)
                .toList();
    }

    public List<Player> getPlayersOlderThan(int age) {
        return players.stream()
                .filter(p -> p.getAge() > age)
                .toList();
    }

    // Collectors
    public Map<String, Integer> getPlayerScoreMap() {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, Player::getScore, (a, b) -> a));
    }

    public Map<PlayerType, List<Player>> groupPlayersByType() {
        return players.stream()
                .collect(Collectors.groupingBy(Player::getType));
    }

    public Map<Boolean, List<Player>> partitionPlayersByAdult(int ageLimit) {
        return players.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= ageLimit));
    }

    // Record mapping
    public List<PlayerSummary> getPlayerSummaries() {
        return players.stream()
                .map(p -> new PlayerSummary(p.getName(), p.getType(), p.getAge(), p.getScore()))
                .toList();
    }
}