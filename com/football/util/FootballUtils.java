package com.football.util;

/**
 * Demonstrates:
 * - method overloading
 * - varargs
 * - StringBuilder (Core API)
 */
public class FootballUtils {

    // Method overloading example
    public static double average(int... nums) {
        if (nums.length == 0) return 0;
        int total = 0;
        for (int n : nums) total += n;
        return (double) total / nums.length;
    }

    public static double average(double... nums) {
        if (nums.length == 0) return 0;
        double total = 0;
        for (double n : nums) total += n;
        return total / nums.length;
    }

    // StringBuilder usage
    public static String formatPlayer(String name, int age) {
        StringBuilder sb = new StringBuilder();
        sb.append("Player: ").append(name).append(", Age: ").append(age);
        return sb.toString();
    }
}
