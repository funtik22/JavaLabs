package ru.aomikhailov;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

import java.io.*;
import java.net.*;


public class URLParser {
    private URL url;
    private final URLConnection urlConnection;

    public URLParser(String url) {
        try {
            this.url = new URI(url).toURL();
        } catch (URISyntaxException e) {
            System.out.println("String could not be parsed as a URI reference");
            Runtime.getRuntime().exit(1);
        } catch (MalformedURLException e) {
            System.out.println("Incorrect URL");
            Runtime.getRuntime().exit(1);
        }
        urlConnection = connect();
    }

    private URLConnection connect() {
        URLConnection urlConnection = null;
        try {
            urlConnection = url.openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            System.out.println("Unable to establish connection");
            Runtime.getRuntime().exit(1);
        }
        return urlConnection;
    }


    private String getResponse() {
        StringBuilder response = new StringBuilder();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Unable to read the answer");
            Runtime.getRuntime().exit(1);
        }
        if (response.isEmpty()) {
            System.out.println("Unable to read the answer");
            Runtime.getRuntime().exit(1);
        }
        return response.toString();
    }

    public JsonArray getJSONList() {
        JsonReader jsonReader = Json.createReader(new StringReader(getResponse()));
        return jsonReader.readArray();
    }
}
