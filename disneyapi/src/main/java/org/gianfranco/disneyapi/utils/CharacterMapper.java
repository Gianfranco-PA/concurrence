package org.gianfranco.disneyapi.utils;

import org.gianfranco.disneyapi.model.Character;
import org.json.JSONObject;

public class CharacterMapper {

    public static Character getCharacter(JSONObject jsonObject) {
        Character character = new Character();

        if (!jsonObject.isNull("_id")) {
            int id = jsonObject.getInt("_id");
            character.setId(id);
        }
        if (!jsonObject.isNull("name")) {
            String title = jsonObject.getString("name");
            character.setName(title);
        }
        if (!jsonObject.isNull("imageUrl")) {
            String imageId = jsonObject.getString("imageUrl");
            character.setImageUrl(imageId);
        }

        return character;
    }
}
