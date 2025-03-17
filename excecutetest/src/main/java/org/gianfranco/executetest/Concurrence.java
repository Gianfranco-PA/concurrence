package org.gianfranco.executetest;

import org.gianfranco.disneyapi.service.Extractor;
import org.gianfranco.disneyapi.model.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrence implements ImageSizeTotal {

    public long get(List<Character> characterList) {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        List<CompletableFuture<Long>> futures = new ArrayList<>();

        for (Character character : characterList) {
            CompletableFuture<Long> future = CompletableFuture
                    .supplyAsync(() -> (long) (
                            Extractor.getImage(character, true).length
                    ), executor);

            futures.add(future);
        }

        return futures.stream().mapToLong(CompletableFuture::join).sum();
    }
}
