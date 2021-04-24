package com.demo.movieapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("id")
    private int movieId;
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<MovieReview> reviews;

    public class MovieReview {
        @SerializedName("author")
        private String author;
        @SerializedName("author_details")
        private AuthorDetail authorDetail;
        @SerializedName("content")
        private String content;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("id")
        private String id;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("url")
        private String url;
    }

    public class AuthorDetail {
        @SerializedName("name")
        private String name;
        @SerializedName("username")
        private String username;
        @SerializedName("avatar_path")
        private String avatarPath;
        @SerializedName("rating")
        private float rating;
    }
}
