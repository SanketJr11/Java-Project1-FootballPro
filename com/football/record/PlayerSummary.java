package com.football.record;

import com.football.model.PlayerType;

public record PlayerSummary(String name, PlayerType type, int age, int score) { }