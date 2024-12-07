package ru.aomikhailov;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import java.util.ArrayList;


public class Continent {
    private final String code;
    private final String name;
    private final int areaSqKm;
    private final int population;
    private final ArrayList<String> lines;
    private final int countries;
    private final ArrayList<String> oceans;
    private final ArrayList<String> developedCountries;


    public Continent(JsonObject jsonObject) {
        code = jsonObject.getString("code");
        name = jsonObject.getString("name");
        areaSqKm = jsonObject.getInt("areaSqKm");
        population = jsonObject.getInt("population");
        lines = JsonArrayToArrayList(jsonObject.getJsonArray("lines"));
        countries = jsonObject.getInt("countries");
        oceans = JsonArrayToArrayList(jsonObject.getJsonArray("oceans"));
        developedCountries = JsonArrayToArrayList(jsonObject.getJsonArray("developedCountries"));
    }

    public Continent(String code, String name, int areaSqKm, int population, ArrayList<String> lines,
                     int countries, ArrayList<String> oceans, ArrayList<String> developedCountries) {
        this.code = code;
        this.name = name;
        this.areaSqKm = areaSqKm;
        this.population = population;
        this.lines = lines;
        this.countries = countries;
        this.oceans = oceans;
        this.developedCountries = developedCountries;
    }

    private ArrayList<String> JsonArrayToArrayList(JsonArray jsonArray) {
        ArrayList<String> out = new ArrayList<>();
        for (JsonValue element : jsonArray) {
            out.add(element.toString());
        }
        return out;
    }


    static public ArrayList<Continent> createContinentsListFromJson(JsonArray jsonArray) {
        ArrayList<Continent> continent = new ArrayList<Continent>();
        for (int i = 0; i < jsonArray.size(); i++) {
            continent.add(new Continent(jsonArray.getJsonObject(i)));
        }
        return continent;
    }

    public String toString() {
        return "code: " + code
                + "\nname: " + name
                + "\nareaSqKm: " + areaSqKm
                + "\npopulation: " + population
                + "\nlines: " + lines
                + "\ncountries: " + countries
                + "\noceans: " + oceans
                + "\ndevelopedCountries: " + developedCountries;
    }
}
