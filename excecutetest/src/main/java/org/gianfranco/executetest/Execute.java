package org.gianfranco.executetest;


import org.gianfranco.artapi.model.Art;
import org.gianfranco.artapi.service.Extractor;

import java.util.List;

public class Execute {

    public static void main(String[] args) {
        List<Art> artList = Extractor.getArts(2,5);

        for (Art art : artList) {
            String title = art.getTitle();
            String imageId = art.getImage_id();

            long imageSizeKB = Extractor.getArtImage(imageId).length / 1024;

            System.out.println(title + "\t" + imageSizeKB + "KB");
        }
    }
}
