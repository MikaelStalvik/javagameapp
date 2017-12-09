package com.imploded.javagameapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Game {
    @SerializedName("Id")
    private String id;
    public String getId() {
        return this.id;
    }
    public void setId(String data) {
        this.id = data;
    }
    @SerializedName("Description")
    private String description;
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String data) {
        this.description = data;
    }
    @SerializedName("Name")
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String data) {
        this.name = data;
    }
    @SerializedName("Publisher")
    private String publisher;
    public String getPublisher() {
        return this.publisher;
    }
    public void setPublisher(String data) {
        this.publisher = data;
    }
    @SerializedName("ReleaseYear")
    private String releaseYear;
    public String getReleaseYear() {
        return this.releaseYear;
    }
    public void setReleaseYear(String data) {
        this.releaseYear = data;
    }
    @SerializedName("Picture")
    private String picture;
    public String getPicture() {
        return this.picture;
    }
    public void setPicture(String data) {
        this.picture = data;
    }
    @SerializedName("Platforms")
    private List<String>  platforms;
    public List<String> getPlatforms() {return this.platforms;}
    public void setPlatforms(List<String> data) {this.platforms = data;}
}
