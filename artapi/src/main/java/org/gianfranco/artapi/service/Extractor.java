package org.gianfranco.artapi.service;

import org.gianfranco.artapi.model.Art;
import org.gianfranco.artapi.utils.ArtMapper;
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

    private static final String API_URL = "https://api.artic.edu/api/v1/artworks";

    public static List<Art> getArts(int page, int limit) {
        String url = API_URL + "?fields=id,title,image_id&page=" + page + "&limit=" + limit;
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
        List<Art> artList = new ArrayList<>();

        for (Object data : dataArray) {
            JSONObject artwork = (JSONObject) data;
            artList.add(ArtMapper.getArt(artwork));
        }

        return artList;
    }

    public static byte[] getArtImage(String imageId) {
        String imageUrl = "https://www.artic.edu/iiif/2/" + imageId + "/full/843,/0/default.jpg";
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

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error - status code: " + response.statusCode());
        }

        return response.body();
    }

}
