package com.football.util;

import com.football.model.Defender;
import com.football.model.Goalkeeper;
import com.football.model.Player;
import com.football.model.Striker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FootballUtils {

    public static double average(int... nums) {
        if (nums.length == 0) return 0;
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        return (double) total / nums.length;
    }

    public static double average(double... nums) {
        if (nums.length == 0) return 0;
        double total = 0;
        for (double n : nums) {
            total += n;
        }
        return total / nums.length;
    }

    public static String formatPlayer(String name, int age) {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ").append(name).append(", Age: ").append(age);
        return sb.toString();
    }

    public static long daysSince(LocalDate joinDate) {
        return ChronoUnit.DAYS.between(joinDate, LocalDate.now());
    }

    public static String formatJoinDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    // Pattern matching for instanceof
    public static String describePlayerByPattern(Player player) {
        if (player instanceof Striker s) {
            return "Pattern match -> Striker: " + s.getName() + ", goals = " + s.getScore();
        } else if (player instanceof Goalkeeper g) {
            return "Pattern match -> Goalkeeper: " + g.getName() + ", saves = " + g.getScore();
        } else if (player instanceof Defender d) {
            return "Pattern match -> Defender: " + d.getName() + ", tackles = " + d.getScore();
        } else {
            return "Unknown player type";
        }
    }
}