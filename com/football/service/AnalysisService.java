package com.football.service;

import com.football.concurrency.PlayerAnalysisTask;
import com.football.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AnalysisService {

    public List<String> analysePlayers(List<Player> players) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();

        for (Player player : players) {
            tasks.add(new PlayerAnalysisTask(player));
        }

        List<String> results = new ArrayList<>();

        try {
            List<Future<String>> futures = executorService.invokeAll(tasks);
            for (Future<String> future : futures) {
                results.add(future.get());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            results.add("Analysis interrupted.");
        } catch (ExecutionException e) {
            results.add("Analysis failed: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }

        return results;
    }
}