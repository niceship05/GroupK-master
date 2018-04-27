package com.example.grant.groupk;

import android.net.Uri;

public class Post {
    private String title;
    private String description;


    public Post(){

    }
    public Post(String title, String description, Uri imageURI){
        this.title = title;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }




}
