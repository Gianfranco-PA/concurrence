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

public class Extractor {

    private static final String API_URL = "https://api.disneyapi.dev/character";

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

    public static byte[] getImage(Character character) {
        return getImage(character, false);
    }

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
