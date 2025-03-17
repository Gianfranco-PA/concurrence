package org.gianfranco.executetest;

import org.gianfranco.disneyapi.model.Character;

import java.util.List;

/**
 * Utility class to measure and print the execution time for image size calculations.
 */
public class CalculateTime {

    /**
     * Measures the time taken by a given ImageSizeTotal implementation to process a list of characters.
     *
     * @param imageSizeTotal an implementation of ImageSizeTotal that calculates the total image size
     * @param characterList  the list of Character objects to process
     */
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
