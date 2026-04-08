package com.football.service;

import com.football.model.*;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public void savePlayers(List<Player> players, String fileName) throws IOException {
        Path path = Paths.get(fileName);

        if (path.getParent() != null && Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        List<String> lines = players.stream()
                .map(p -> p.getName() + "," + p.getAge() + "," + p.getScore() + "," + p.getType() + "," + p.getJoinDate())
                .toList();

        Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public List<Player> loadPlayers(String fileName) throws IOException {
        Path path = Paths.get(fileName);

        if (Files.notExists(path)) {
            return new ArrayList<>();
        }

        List<String> lines = Files.readAllLines(path);
        List<Player> players = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 5) {
                continue;
            }

            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            int score = Integer.parseInt(parts[2]);
            String type = parts[3];
            LocalDate joinDate = LocalDate.parse(parts[4]);

            Player player = switch (type) {
                case "STRIKER" -> new Striker(name, age, score, joinDate);
                case "GOALKEEPER" -> new Goalkeeper(name, age, score, joinDate);
                case "DEFENDER" -> new Defender(name, age, score, joinDate);
                default -> null;
            };

            if (player != null) {
                players.add(player);
            }
        }

        return players;
    }
}