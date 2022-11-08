package com.example.imageproject;

public class Image {
    private String pathUrl;

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public Image(String pathUrl) {
        this.pathUrl = pathUrl;
    }
}
