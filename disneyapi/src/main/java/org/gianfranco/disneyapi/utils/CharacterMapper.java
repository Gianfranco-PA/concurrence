package org.gianfranco.disneyapi.utils;

import org.gianfranco.disneyapi.model.Character;
import org.json.JSONObject;

/**
 * Utility class for mapping JSON objects to Character instances.
 */
public class CharacterMapper {

    /**
     * Maps a JSONObject to a Character object.
     *
     * @param jsonObject the JSON object containing character data
     * @return a Character object with data populated from the JSON object
     */
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
