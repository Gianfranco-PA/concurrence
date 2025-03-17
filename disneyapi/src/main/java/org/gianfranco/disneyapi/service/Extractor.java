package org.gianfranco.disneyapi.service;

import org.gianfranco.disneyapi.model.Character;
import org.gianfranco.disneyapi.utils.CharacterMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to extract Disney character data and images from the Disney API.
 */
public class Extractor {

    private static final String API_URL = "https://api.disneyapi.dev/character";

    /**
     * Retrieves a list of characters from the Disney API.
     *
     * @param page  the page number to retrieve
     * @param limit the maximum number of characters per page
     * @return a list of Character objects
     * @throws RuntimeException if the API request fails or returns a non-200 status code
     */
    public static List<Character> getCharacters(int page, int limit) {
        String url = API_URL + "?&page=" + page + "&pageSize=" + limit;
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error" + e);
        }
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error - status code: " + response.statusCode());
        }
        String responseBody = response.body();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        List<Character> characterList = new ArrayList<>();

        for (Object data : dataArray) {
            JSONObject disneyWork = (JSONObject) data;
            characterList.add(CharacterMapper.getCharacter(disneyWork));
        }

        return characterList;
    }

    /**
     * Retrieves the image data for a given character.
     *
     * @param character the Character object containing the image URL
     * @return a byte array representing the image data
     * @throws RuntimeException if the API request fails or returns a non-200 status code
     */
    public static byte[] getImage(Character character) {
        return getImage(character, false);
    }

    /**
     * Retrieves the image data for a given character, with an option to ignore HTTP status code errors.
     *
     * @param character the Character object containing the image URL
     * @param ignoreStatusCode if true, the method will return the image data regardless of the HTTP status code
     * @return a byte array representing the image data
     * @throws RuntimeException if the API request fails or the status code is not 200 (unless ignored)
     */
    public static byte[] getImage(Character character, boolean ignoreStatusCode) {
        String imageUrl = character.getImageUrl();
        HttpResponse<byte[]> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(imageUrl))
                    .build();
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error:" + e);
            }
        }

        if (response.statusCode() != 200 && !ignoreStatusCode) {
            throw new RuntimeException("Error - status code: " + response.statusCode());
        }

        return response.body();
    }

}
