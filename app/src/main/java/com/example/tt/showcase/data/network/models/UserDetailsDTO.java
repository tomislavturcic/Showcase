package com.example.tt.showcase.data.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TT on 11.3.2018..
 * Showcase
 */

public class UserDetailsDTO extends UserDTO {

    @SerializedName("name")
    private String name;

    @SerializedName("company")
    private String company;

    @SerializedName("blog")
    private String blog;

    @SerializedName("location")
    private String location;

    @SerializedName("email")
    private String email;

    @SerializedName("hireable")
    private Boolean hireable;

    @SerializedName("bio")
    private String bio;

    @SerializedName("public_repos")
    private int publicRepos;

    @SerializedName("public_gists")
    private int publicGists;

    @SerializedName("followers")
    private int followers;

    @SerializedName("following")
    private int following;

    @SerializedName("created_at")
    private String createdAt;           // date format: 2007-10-20T05:24:19Z

    @SerializedName("updated_at")
    private String updatedAt;           // date format: 2007-10-20T05:24:19Z

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getHireable() {
        return hireable;
    }

    public String getBio() {
        return bio;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
