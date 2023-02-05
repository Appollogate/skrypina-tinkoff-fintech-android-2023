package com.tinkoff.skrypina_tinkoff_fintech_2023.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequestHandler {

    static final String TOP_100_FILMS_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS";
    static final String FILM_INFO_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films";
    static final String HEADER_KEY_NAME = "X-API-KEY";
    static final String HEADER_KEY_VALUE = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b";
    static final String HEADER_ACCEPT_KEY = "accept";
    static final String HEADER_ACCEPT_VALUE = "application/json";

    public String getTop100FilmsJSON() {
        return getJSONFromServer(TOP_100_FILMS_URL);
    }

    public String getFilmDataByIdJSON(String id) {
        return getJSONFromServer(FILM_INFO_URL + "/" + id);
    }

    private String getJSONFromServer(String address) {
        URL url;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("URL " + address + "is malformed.\n");
        }

        HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't open connection to " + address);
        }

        // Set headers
        conn.setRequestProperty(HEADER_ACCEPT_KEY, HEADER_ACCEPT_VALUE);
        conn.setRequestProperty(HEADER_KEY_NAME, HEADER_KEY_VALUE);
        try {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Request failed. HTTP error code: " + conn.getResponseCode());
            }
            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            conn.disconnect();
            return jsonString.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            // TODO: handle
            throw new RuntimeException("Couldn't read response from server.\n");
        }
    }
}
