package ru.aomikhailov;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

import java.util.ArrayList;

public class Post {
    private final int userId;
    private final int id;
    private final String title;
    private final String body;
    private final String link;
    private final int comment_count;

    public Post(int userId, int id, String title, String body, String link, int comment_count) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.link = link;
        this.comment_count = comment_count;
    }


    public Post(JsonObject jsonObject) {
        userId = jsonObject.getInt("userId");
        id = jsonObject.getInt("id");
        title = jsonObject.getString("title");
        body = jsonObject.getString("body");
        link = jsonObject.getString("link");
        comment_count = jsonObject.getInt("comment_count");
    }


    static public ArrayList<Post> createPostsListFromJson(JsonArray jsonArray){
        ArrayList<Post> posts = new ArrayList<Post>();
        for(int i = 0; i<jsonArray.size(); i++){
            posts.add(new Post(jsonArray.getJsonObject(i)));
        }
        return posts;
    }

    public String toString(){
        return "userId: " + userId
                +"\nid: " + id
                +"\ntitle: " + title
                +"\nbody: " + body
                +"\nlink: " + link
                +"\ncomment_count: "+comment_count;
    }
}
