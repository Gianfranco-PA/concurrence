package org.gianfranco.executetest;

import org.gianfranco.disneyapi.model.Character;

import java.util.List;

/**
 * Interface for calculating the total image size from a list of characters.
 */
public interface ImageSizeTotal {

    /**
     * Calculates the total size of images for the given list of characters.
     *
     * @param characterList the list of Character objects
     * @return the total size in bytes
     */
    long get(List<Character> characterList);
}
