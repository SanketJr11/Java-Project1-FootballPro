package com.football.record;

/**
 * Demonstrates:
 * - records (Java 16+)
 * - automatically creates constructor, equals(), hashCode(), toString()
 */
public record MatchRecord(String team, int goals, int assists) { }
