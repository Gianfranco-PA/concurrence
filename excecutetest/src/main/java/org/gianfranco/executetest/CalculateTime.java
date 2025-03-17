package org.gianfranco.executetest;

import org.gianfranco.disneyapi.model.Character;

import java.util.List;

public class CalculateTime {

    public static void calculate(ImageSizeTotal imageSizeTotal, List<Character> characterList) {
        long startTime = System.currentTimeMillis();
        imageSizeTotal.get(characterList);
        long endTime = System.currentTimeMillis();

        System.out.println("=============================================");
        System.out.println("Class: " + imageSizeTotal.getClass().getSimpleName());
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Total characters: " + characterList.size());
        System.out.println("=============================================");
    }
}
