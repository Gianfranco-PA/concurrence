package org.gianfranco.artapi.utils;

import org.gianfranco.artapi.model.Art;
import org.json.JSONObject;

public class ArtMapper {
    
    public static Art getArt(JSONObject jsonObject) {
        Art art = new Art();

        if (!jsonObject.isNull("id")) {
            int id = jsonObject.getInt("id");
            art.setId(id);
        }
        if (!jsonObject.isNull("title")) {
            String title = jsonObject.getString("title");
            art.setTitle(title);
        }
        if (!jsonObject.isNull("image_id")) {
            String imageId = jsonObject.getString("image_id");
            art.setImage_id(imageId);
        }

        return art;
    }
}
