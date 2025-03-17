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

        Sequential sequential = new Sequential();
        Concurrence concurrence = new Concurrence();

        CalculateTime.calculate(concurrence, characterList);
        CalculateTime.calculate(sequential, characterList);
    }
}
