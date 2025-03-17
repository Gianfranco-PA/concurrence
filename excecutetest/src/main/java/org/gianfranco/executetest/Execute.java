package org.gianfranco.executetest;


import org.gianfranco.disneyapi.model.Character;
import org.gianfranco.disneyapi.service.Extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Execute {

    public static void main(String[] args) {
        List<Character> characterList = Extractor.getCharacters(1, 50)
                .stream()
                .filter(character ->
                        character.getImageUrl() != null)
                .toList();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<CompletableFuture<Long>> futures = new ArrayList<>();

        for (Character character : characterList) {
            CompletableFuture<Long> future = CompletableFuture
                    .supplyAsync(() -> (long) (
                                    Extractor.getImage(character, true).length
                            ), executor);

            futures.add(future);
        }

        long totalBytes = futures.stream().mapToLong(CompletableFuture::join).sum();
        long totalKB = totalBytes / 1024;
        long totalMB = totalKB / 1024;

        System.out.println("Total KB: " + totalKB + "KB");
        System.out.println("Total MB: " + totalMB + "MB");

        executor.shutdown();
    }
}
