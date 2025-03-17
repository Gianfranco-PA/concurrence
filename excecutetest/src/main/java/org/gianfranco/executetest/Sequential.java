package org.gianfranco.executetest;

import org.gianfranco.disneyapi.model.Character;
import org.gianfranco.disneyapi.service.Extractor;

import java.util.List;

public class Sequential implements ImageSizeTotal {

    public long get(List<Character> characterList) {

        long sum = 0;

        for (Character character : characterList) {
            sum += Extractor.getImage(character,true).length;
        }

        return sum;
    }
}
