package ru.aomikhailov;


import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        URLParser urlPostParser = new URLParser("https://dummy-json.mock.beeceptor.com/posts");
        ArrayList<Post> posts = Post.createPostsListFromJson(urlPostParser.getJSONList());
        for (Post post : posts) {
            System.out.println(post);
            System.out.println("+++++++++++++++++++++++++++++");
        }

        System.out.println("-------------------------------------------------------------------");

        URLParser urlContinentParser = new URLParser("https://dummy-json.mock.beeceptor.com/continents");
        ArrayList<Continent> continents = Continent.createContinentsListFromJson(urlContinentParser.getJSONList());
        for (Continent continent : continents) {
            System.out.println(continent);
            System.out.println("+++++++++++++++++++++++++++++");
        }
    }
}